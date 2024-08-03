/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Marcelo
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
*
* @author Albert Eije
*/
public class Assinador {

private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
private static final String PROVIDER_CLASS_NAME = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
private static final String PROVIDER_NAME = "jsr105Provider";

public void assinar(String arquivoXML, String arquivoCertificado,
String password, String arquivoXMLNovo, String operacao) throws Exception {

/*
operacao
'1' - NFE
'2' - CANCELAMENTO
'3' - INUTILIZACAO
*/
String tag = "";
if (operacao.equals("1")) {
tag = "infNFe";
} else if (operacao.equals("2")) {
tag = "infCanc";
} else if (operacao.equals("3")) {
tag = "infInut";
} else if (operacao.equals("4")) {
tag = "infEvento";}

DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
factory.setNamespaceAware(false);
DocumentBuilder builder = factory.newDocumentBuilder();
Document docs = builder.parse(new File(arquivoXML));
NodeList elements = docs.getElementsByTagName(tag);
Element el = (Element) elements.item(0);
String id = el.getAttribute("Id");

// Cria um DOM do tipo XMLSignatureFactory que serÃ¡ utilizado
// para gerar a assinatura envelopada (enveloped signature)
String providerName = System.getProperty(PROVIDER_NAME,
PROVIDER_CLASS_NAME);

XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM",
(Provider) Class.forName(providerName).newInstance());

// Define os algoritmos de transformaÃ§Ã£o
ArrayList transformList = new ArrayList();
TransformParameterSpec tps = null;
Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED,
tps);
Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
transformList.add(envelopedTransform);
transformList.add(c14NTransform);

// Cria o objeto Reference
Reference ref = fac.newReference("#" + id, fac.newDigestMethod(
DigestMethod.SHA1, null), transformList, null, null);

// Cria o elemento SignedInfo
SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
CanonicalizationMethod.INCLUSIVE,
(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
Collections.singletonList(ref));

// Carrega o KeyStore e obtem a chave do certificado
KeyStore ks = KeyStore.getInstance("PKCS12");
ks.load(new FileInputStream(arquivoCertificado), password.toCharArray());
Enumeration aliasesEnum = ks.aliases();
String alias = "";
while (aliasesEnum.hasMoreElements()) {
alias = (String) aliasesEnum.nextElement();
if (ks.isKeyEntry(alias)) {
break;
}
}

KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));

// Instancia um certificado do tipo X509
X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

// Cria o elemente KeyInfo contendo a X509Data.
KeyInfoFactory kif = fac.getKeyInfoFactory();
List x509Content = new ArrayList();
x509Content.add(cert);
X509Data xd = kif.newX509Data(x509Content);
KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

// Instancia o documento que serÃ¡ assinado
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
dbf.setNamespaceAware(true);
Document doc = dbf.newDocumentBuilder().parse(
new FileInputStream(arquivoXML));

// Cria o DOMSignContext especificando a chave e o nÃ³ pai
DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

// Cria a XMLSignature, mas nÃ£o assina ainda
XMLSignature signature = fac.newXMLSignature(si, ki);

// Empacota (marshal), gera e assina
signature.sign(dsc);

// Arquivo novo assinado
OutputStream os = new FileOutputStream(arquivoXMLNovo);
TransformerFactory tf = TransformerFactory.newInstance();
Transformer trans = tf.newTransformer();
trans.transform(new DOMSource(doc), new StreamResult(os));

// Encontra o elemente Signature
NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS,
"Signature");

if (nl.getLength() == 0) {
throw new Exception("NÃ£o foi possÃ­vel encontrar o elemente Signature");
}

// Cria um DOMValidateContext
DOMValidateContext valContext = new DOMValidateContext(
new X509KeySelector(ks), nl.item(0));

// Dsempacota (unmarshal) a XMLSignature
XMLSignature signatures = fac.unmarshalXMLSignature(valContext);

// Valida a XMLSignature.
boolean coreValidity = signatures.validate(valContext);

// Checa o status da validaÃ§Ã£o
if (coreValidity == false) {
System.err.println("Falha na Assinatura!");
} else {
System.out.println("Assinatura Correta!");
}
}

public static void main(String[] args) throws Exception {

/*if (args.length != 5) {
System.out.println("Passe os cinco parÃ¢metros necessÃ¡rios");
return;
}*/
String caminhoXml = "C:/Users/Murilo/Desktop/MINHA.xml";
String caminhoCertificado = "C:/certificados/Associacao.p12";
String senha = "123";
String arquivoXmlNovo = "C:/Users/Murilo/Desktop/MINHA_2.xml";
String tipo = "1";

File file = new File(caminhoXml);
if (!file.exists()) {
System.out.println("Arquivo " + caminhoXml + " nÃ£o encontrado!");
return;
}
file = new File(caminhoCertificado);
if (!file.exists()) {
System.out.println("Arquivo " + caminhoCertificado + " nÃ£o encontrado!");
return;
}
try {
Assinador t = new Assinador();
t.assinar(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo,
tipo);
System.out.println("Arquivo XML assinado com sucesso" + caminhoXml + "!");
} catch (Exception e) {
System.out.println("Erro ao tentar assinar arquivo XML! \n\n" + e.toString());
}
}
}
