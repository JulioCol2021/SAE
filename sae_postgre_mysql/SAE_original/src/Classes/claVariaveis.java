/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

/**
 *
 * @author Thiago
 */
public class claVariaveis {
    
    public int usuarioID,
                   contador,
                   tamanho,
                   programaID;
        public boolean  superUsuario,
                    sair,
                    restricao,
                    incluir = true,
                    alterar = true,
                    excluir = true,
                    imprimir = true,
                    pesq = true,
                    aborta;
        public static String xmlAssinado , status, status_det,
                      usuario_id, nome_usuario, usuario_super, tipo_grupo, vg_PosicaoFinanceira, vm_Pedido,
                      empresa, vmCampos, vmParametros, vmProdutoID, vmUnidadeMedida, vmLote, vmEmpresaID, vmErrosXML, vmCotacao, CNPJ, Linha;
        public static int vm_ID;
        public static double vm_IPI, vm_ICMS, vm_Desconto, vm_Total, vm_base_calculo_icms, vm_valor, vm_valor_pendente;
        public static String xColuna1, xColuna2, xColuna3, xColuna4, xColuna5, xColuna6, xColuna7, xColuna8, xColuna9, xColunaStart, xColunaNomeStart, xSql,
                             xTabela, xColunaNome1, xColunaNome2, xColunaNome3, xColunaNome4, xColunaNome5, xColunaNome6, xColunaNome7, xColunaNome8, xColunaNome9; 
        public static int  xColunaTamanho1 , xColunaTamanho2, xColunaTamanho3 ,xColunaTamanho4, xColunaTamanho5, xColunaTamanho6, xColunaTamanho7, xColunaTamanho8, xColunaTamanho9;
        public static String xCondicaoAND = "";
        public void Variaveis()
        {
            usuarioID = 0;
            contador = 0;
            tamanho = 0;
            programaID = 0;
            incluir = true;
            alterar = true;
            excluir = true;
            imprimir = true;
            pesq = true;
            aborta = false;
            status = null;
            status_det = null;
            usuario_id = null;
            nome_usuario = "";
            usuario_super = null;
            vm_ID = 0;
            tipo_grupo = null;
            vg_PosicaoFinanceira = null;
            vm_ICMS = 0;
            vm_IPI = 0;
            vm_Total = 0;
            vm_Desconto = 0;
            vm_base_calculo_icms = 0;
            vm_Pedido = null;
            empresa = null;
            vm_valor = 0;
            vmCampos ="";
            vmParametros="";
            vmProdutoID = "";
            vmUnidadeMedida = "";
            vmLote ="";
            vmEmpresaID = "1";
            vmErrosXML = "";
            vmCotacao = "";
            xCondicaoAND = "";
            String Linha = "";
         }
}
