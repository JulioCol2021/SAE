﻿--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: SAE; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "SA" IS 'Banco de Dados www.mtds.com.br by thiago';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

--
-- Name: lo; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN lo AS oid;


ALTER DOMAIN public.lo OWNER TO postgres;

--
-- Name: fu_extenso(numeric, text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION fu_extenso(num numeric, moeda text, moedas text) RETURNS text
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $$
-- num -> numero a ser convertido em extenso
-- moeda -> nome da moeda no singular
-- moedas -> nome da moeda no plural
declare
w_int char(21) ;
x integer ;
v integer ;
w_ret text ;
w_ext text ;
w_apoio text ;
m_cen text[] := 
array['quatrilhão','quatrilhões','trilhão','trilhões','bilhão','bilhões','milhão','milhões','mil','mil']
 ;
begin
  w_ret := '' ;
  w_int := to_char(num * 100 , 'fm000000000000000000 00') ;
  for x in 1..5 loop
      v := cast(substr(w_int,(x-1)*3 + 1,3) as integer) ;    
      if v > 0 then
         if v > 1 then
            w_ext := m_cen[(x-1)*2+2] ;
           else
            w_ext := m_cen[(x-1)*2+1] ;
         end if ;   
         w_ret := w_ret || fu_extenso_blk(substr(w_int,(x-1)*3 + 1,3)) || ' ' 
|| w_ext ||', ' ;
      end if ;  
  end loop ;
  v := cast(substr(w_int,16,3) as integer) ;    
  if v > 0 then
     if v > 1 then
        w_ext := moedas ;
       else
        if w_ret = '' then 
           w_ext := moeda ;
          else
           w_ext := moedas ;
        end if ;   
     end if ; 
     w_apoio := fu_extenso_blk(substr(w_int,16,3)) || ' ' || w_ext ;
     if w_ret = '' then 
        w_ret := w_apoio ;
       else 
        if v > 100 then 
           if w_ret = '' then 
              w_ret := w_apoio ;
             else
              w_ret := w_ret || w_apoio ;
           end if ;   
          else
           w_ret := btrim(w_ret,', ') || ' e ' || w_apoio ;
        end if ;   
     end if ;   
    else 
     if w_ret <> '' then  
        if substr(w_int,13,6) = '000000' then 
           w_ret := btrim(w_ret,', ') || ' de ' || moedas ;
          else 
           w_ret := btrim(w_ret,', ') || ' ' || moedas ;
        end if ;    
     end if ;  
  end if ;    
  v := cast(substr(w_int,20,2) as integer) ;    
  if v > 0 then
     if v > 1 then
        w_ext := 'centavos' ;
       else
        w_ext := 'centavo' ;
     end if ;   
     w_apoio := fu_extenso_blk('0'||substr(w_int,20,2)) || ' ' || w_ext ;
     if w_ret = '' then 
        w_ret := w_apoio  || ' de ' || moeda;
       else 
        w_ret := w_ret || ' e ' || w_apoio ;
     end if ;   
  end if ;    
  return w_ret ;  
end ;
$$;


ALTER FUNCTION public.fu_extenso(num numeric, moeda text, moedas text) OWNER TO postgres;

--
-- Name: fu_extenso_blk(character); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION fu_extenso_blk(num character) RETURNS text
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $$
declare
w_cen integer ;
w_dez integer ;
w_dez2 integer ;
w_uni integer ;
w_tcen text ;
w_tdez text ;
w_tuni text ;
w_ext text ;
m_cen text[] := 
array['','cento','duzentos','trezentos','quatrocentos','quinhentos','seiscentos','setecentos','oitocentos','novecentos'];
m_dez text[] := 
array['','dez','vinte','trinta','quarenta','cinquenta','sessenta','setenta','oitenta','noventa']
 ;
m_uni text[] := 
array['','um','dois','três','quatro','cinco','seis','sete','oito','nove','dez','onze','doze','treze','quatorze','quinze','dezesseis','dezessete','dezoito','dezenove']
 ;
begin
  w_cen := cast(substr(num,1,1) as integer) ;
  w_dez := cast(substr(num,2,1) as integer) ;
  w_dez2 := cast(substr(num,2,2) as integer) ;
  w_uni := cast(substr(num,3,1) as integer) ;
  if w_cen = 1 and w_dez2 = 0 then
     w_tcen := 'Cem' ;
     w_tdez := '' ;
     w_tuni := '' ;
    else
     if w_dez2 < 20 then 
        w_tcen := m_cen[w_cen + 1] ;
        w_tdez := m_uni[w_dez2 + 1] ; 
        w_tuni := '' ;
       else
        w_tcen := m_cen[w_cen + 1] ;
        w_tdez := m_dez[w_dez + 1] ; 
        w_tuni := m_uni[w_uni + 1] ;
     end if ;    
  end if ; 
  w_ext := w_tcen ;
  if w_tdez <> '' then  
     if w_ext = '' then 
        w_ext := w_tdez ;
       else
        w_ext := w_ext || ' e ' || w_tdez ;
     end if ;      
  end if ;   
  if w_tuni <> '' then  
     if w_ext = '' then 
        w_ext := w_tuni ;
       else
        w_ext := w_ext || ' e ' || w_tuni ;
     end if ;
  end if ;
  return w_ext ;  
end ;
$$;


ALTER FUNCTION public.fu_extenso_blk(num character) OWNER TO postgres;

--
-- Name: fu_extenso_real(numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION fu_extenso_real(num numeric) RETURNS text
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $$
-- num -> numero a ser convertido em extenso
begin
  return fu_extenso(num,'real','reais') ;  
end ;
$$;


ALTER FUNCTION public.fu_extenso_real(num numeric) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: almoxarifados; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE almoxarifados (
    almox_codigo_id integer NOT NULL,
    almox_descricao character varying(60)
);


ALTER TABLE public.almoxarifados OWNER TO postgres;

--
-- Name: almoxarifados_almox_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE almoxarifados_almox_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.almoxarifados_almox_codigo_id_seq OWNER TO postgres;

--
-- Name: almoxarifados_almox_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE almoxarifados_almox_codigo_id_seq OWNED BY almoxarifados.almox_codigo_id;


--
-- Name: almoxarifados_almox_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('almoxarifados_almox_codigo_id_seq', 0, true);


--
-- Name: centro_custo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE centro_custo (
    custo_codigo_id integer NOT NULL,
    custo_centro_custo character varying(25),
    custo_descricao character varying(60)
);


ALTER TABLE public.centro_custo OWNER TO postgres;

--
-- Name: centro_custo_custo_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE centro_custo_custo_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.centro_custo_custo_codigo_id_seq OWNER TO postgres;

--
-- Name: centro_custo_custo_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE centro_custo_custo_codigo_id_seq OWNED BY centro_custo.custo_codigo_id;


--
-- Name: centro_custo_custo_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('centro_custo_custo_codigo_id_seq', 0, false);


--
-- Name: cfop; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cfop (
    cfop_codigo_id integer NOT NULL,
    cfop_cfop character varying(5),
    cfop_descricao character varying(80),
    cfop_msg1_codigo_id integer,
    cfop_msg2_codigo_id integer,
    cfop_msg3_codigo_id integer,
    cfop_msg4_codigo_id integer,
    cfop_incorporar_ipi character varying(3)
);


ALTER TABLE public.cfop OWNER TO postgres;

--
-- Name: cfop_cfop_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cfop_cfop_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cfop_cfop_codigo_id_seq OWNER TO postgres;

--
-- Name: cfop_cfop_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cfop_cfop_codigo_id_seq OWNED BY cfop.cfop_codigo_id;


--
-- Name: cfop_cfop_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cfop_cfop_codigo_id_seq', 0, true);


--
-- Name: cidades; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidades (
    cid_codigo_id integer NOT NULL,
    cid_codigo_uf integer,
    cid_uf character varying(2),
    cid_codigo_municipio integer,
    cid_municipio character varying(60)
);


ALTER TABLE public.cidades OWNER TO postgres;

--
-- Name: cidades_cid_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cidades_cid_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cidades_cid_codigo_id_seq OWNER TO postgres;

--
-- Name: cidades_cid_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cidades_cid_codigo_id_seq OWNED BY cidades.cid_codigo_id;


--
-- Name: cidades_cid_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cidades_cid_codigo_id_seq', 0, false);


--
-- Name: condicao_pagamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE condicao_pagamento (
    cpgto_codigo_id integer NOT NULL,
    cpgto_descricao character varying(255),
    cpgto_qtde_parcelas integer,
    cpgto_prazo_medio integer
);


ALTER TABLE public.condicao_pagamento OWNER TO postgres;

--
-- Name: condicao_pagamento_cpgto_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE condicao_pagamento_cpgto_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.condicao_pagamento_cpgto_codigo_id_seq OWNER TO postgres;

--
-- Name: condicao_pagamento_cpgto_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE condicao_pagamento_cpgto_codigo_id_seq OWNED BY condicao_pagamento.cpgto_codigo_id;


--
-- Name: condicao_pagamento_cpgto_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('condicao_pagamento_cpgto_codigo_id_seq', 0, true);


--
-- Name: contas_bancarias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contas_bancarias (
    banc_codigo_id integer NOT NULL,
    banc_numero_banco integer,
    banc_nome_banco character varying(40),
    banc_numero_agencia integer,
    banc_nome_agencia character varying(40),
    banc_numero_conta character varying(20),
    banc_convenio character varying(20)
);


ALTER TABLE public.contas_bancarias OWNER TO postgres;

--
-- Name: contas_bancarias_banc_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contas_bancarias_banc_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contas_bancarias_banc_codigo_id_seq OWNER TO postgres;

--
-- Name: contas_bancarias_banc_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contas_bancarias_banc_codigo_id_seq OWNED BY contas_bancarias.banc_codigo_id;


--
-- Name: contas_bancarias_banc_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contas_bancarias_banc_codigo_id_seq', 0, true);


--
-- Name: contas_corrente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contas_corrente (
    cc_codigo_id integer NOT NULL,
    cc_descricao character varying(70),
    cc_banc_codigo_id integer,
    cc_data_lancamento date,
    cc_documento character varying(20),
    cc_numero_cheque character varying(20),
    cc_historico character varying(255),
    cc_credito double precision,
    cc_debito double precision,
    cc_realizado character varying(5),
    cc_cp_codigo_id integer,
    cc_cr_codigo_id integer,
    cc_nota integer,
    cc_data_vencimento date
);


ALTER TABLE public.contas_corrente OWNER TO postgres;

--
-- Name: contas_corrente_cc_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contas_corrente_cc_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contas_corrente_cc_codigo_id_seq OWNER TO postgres;

--
-- Name: contas_corrente_cc_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contas_corrente_cc_codigo_id_seq OWNED BY contas_corrente.cc_codigo_id;


--
-- Name: contas_corrente_cc_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contas_corrente_cc_codigo_id_seq', 0, true);


--
-- Name: contas_pagar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contas_pagar (
    cp_codigo_id integer NOT NULL,
    cp_emp_codigo_id integer,
    cp_par_codigo_id integer,
    cp_nota_fiscal integer,
    cp_serie_nota character varying(2),
    cp_documento character varying(20),
    cp_classificacao character varying(1),
    cp_parcela integer,
    cp_data_emissao date,
    cp_data_vencimento date,
    cp_valor_documento double precision,
    cp_fpgto_codigo_id integer,
    cp_cpgto_codigo_id integer,
    cp_doc_codigo_id integer,
    cp_custo_codigo_id integer,
    cp_observacao character varying(255),
    cp_data_pagamento date,
    cp_acrescimo double precision,
    cp_desconto double precision,
    cp_valor_pago double precision,
    cp_banc_codigo_id integer,
    cp_favorecido character varying(100),
    cp_observacao_baixa character varying(255),
    cp_usuario character varying(20),
    cp_usuario_alt character varying(20),
    cp_total_parcelas integer
);


ALTER TABLE public.contas_pagar OWNER TO postgres;

--
-- Name: contas_pagar_cp_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contas_pagar_cp_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contas_pagar_cp_codigo_id_seq OWNER TO postgres;

--
-- Name: contas_pagar_cp_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contas_pagar_cp_codigo_id_seq OWNED BY contas_pagar.cp_codigo_id;


--
-- Name: contas_pagar_cp_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contas_pagar_cp_codigo_id_seq', 0, true);


--
-- Name: contas_receber; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contas_receber (
    cr_codigo_id integer NOT NULL,
    cr_emp_codigo_id integer,
    cr_par_codigo_id integer,
    cr_nota_fiscal integer,
    cr_serie_nota character varying(2),
    cr_documento character varying(20),
    cr_classificacao character varying(1),
    cr_parcela integer,
    cr_data_emissao date,
    cr_data_vencimento date,
    cr_valor_documento double precision,
    cr_fpgto_codigo_id integer,
    cr_cpgto_codigo_id integer,
    cr_doc_codigo_id integer,
    cr_custo_codigo_id integer,
    cr_observacao character varying(255),
    cr_data_pagamento date,
    cr_acrescimo double precision,
    cr_desconto double precision,
    cr_valor_pago double precision,
    cr_banc_codigo_id integer,
    cr_favorecido character varying(100),
    cr_observacao_baixa character varying(255),
    cr_usuario character varying(20),
    cr_usuario_alt character varying(20),
    cr_total_parcelas integer
);


ALTER TABLE public.contas_receber OWNER TO postgres;

--
-- Name: contas_receber_cr_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contas_receber_cr_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contas_receber_cr_codigo_id_seq OWNER TO postgres;

--
-- Name: contas_receber_cr_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contas_receber_cr_codigo_id_seq OWNED BY contas_receber.cr_codigo_id;


--
-- Name: contas_receber_cr_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contas_receber_cr_codigo_id_seq', 0, true);


--
-- Name: cotacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cotacao (
    cot_codigo_id integer NOT NULL,
    cot_numero_solicitacao integer,
    cot_data date,
    cot_data_entrega date,
    cot_cpgto_codigo_id integer,
    cot_observacao character varying(255),
    cot_frete double precision,
    com_usuario character varying(50)
);


ALTER TABLE public.cotacao OWNER TO postgres;

--
-- Name: cotacao_cot_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cotacao_cot_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cotacao_cot_codigo_id_seq OWNER TO postgres;

--
-- Name: cotacao_cot_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cotacao_cot_codigo_id_seq OWNED BY cotacao.cot_codigo_id;


--
-- Name: cotacao_cot_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cotacao_cot_codigo_id_seq', 0, true);


--
-- Name: departamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE departamento (
    depa_codigo_id integer NOT NULL,
    depa_descricao character varying(60)
);


ALTER TABLE public.departamento OWNER TO postgres;

--
-- Name: departamento_depa_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE departamento_depa_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departamento_depa_codigo_id_seq OWNER TO postgres;

--
-- Name: departamento_depa_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE departamento_depa_codigo_id_seq OWNED BY departamento.depa_codigo_id;


--
-- Name: departamento_depa_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('departamento_depa_codigo_id_seq', 0, true);


--
-- Name: empresas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empresas (
    emp_codigo_id integer NOT NULL,
    emp_nome character varying(50),
    emp_nome_fantasia character varying(50),
    emp_reduzido character varying(15),
    emp_cnpj character varying(18),
    emp_insc_estadual character varying(15),
    emp_insc_municipal character varying(20),
    emp_endereco character varying(50),
    emp_numero integer,
    emp_bairro character varying(50),
    emp_cid_codigo_id integer,
    emp_complemento character varying(30),
    emp_cep character varying(9),
    emp_telefone character varying(13),
    emp_fax character varying(13),
    emp_email character varying(50),
    emp_homepage character varying(50),
    emp_logo_marca character varying(120),
    emp_registro_contabil integer,
    emp_nome_contabil character varying(100),
    emp_cpf_contabil character varying(18),
    emp_crc_contabil character varying(15),
    emp_cnpj_contabil character varying(18),
    emp_endereco_contabil character varying(60),
    emp_numero_contabil integer,
    emp_cep_contabil character varying(9),
    emp_bairro_contabil character varying(60),
    emp_cid_codigo_id_contabil integer,
    emp_complemento_contabil character varying(60),
    emp_telefone_contabil character varying(13),
    emp_fax_contabil character varying(13),
    emp_email_contabil character varying(60)
);


ALTER TABLE public.empresas OWNER TO postgres;

--
-- Name: empresas_emp_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE empresas_emp_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresas_emp_codigo_id_seq OWNER TO postgres;

--
-- Name: empresas_emp_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empresas_emp_codigo_id_seq OWNED BY empresas.emp_codigo_id;


--
-- Name: empresas_emp_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresas_emp_codigo_id_seq', 0, true);


--
-- Name: entrada_interna; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE entrada_interna (
    ent_codigo_id integer NOT NULL,
    ent_emp_codigo_id integer,
    ent_par_codigo_id integer,
    ent_data date,
    ent_controle_interno character varying(20),
    ent_observacao character varying(255),
    ent_usuario character varying(50)
);


ALTER TABLE public.entrada_interna OWNER TO postgres;

--
-- Name: entrada_interna_ent_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE entrada_interna_ent_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.entrada_interna_ent_codigo_id_seq OWNER TO postgres;

--
-- Name: entrada_interna_ent_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE entrada_interna_ent_codigo_id_seq OWNED BY entrada_interna.ent_codigo_id;


--
-- Name: entrada_interna_ent_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('entrada_interna_ent_codigo_id_seq', 0, true);


--
-- Name: entrada_notas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE entrada_notas (
    ent_codigo_id integer NOT NULL,
    ent_emp_codigo_id integer,
    ent_data_emissao date,
    ent_data_entrada date,
    ent_chave_nfe character varying(70),
    ent_protocolo_autorizacao character varying(20),
    ent_numero_fatura character varying(20),
    ent_numero_nota integer,
    ent_serie_nota character varying(3),
    ent_base_calculo_icms double precision,
    ent_valor_icms double precision,
    ent_base_calculo_st double precision,
    ent_valor_st double precision,
    ent_valor_frete double precision,
    ent_valor_seguro double precision,
    ent_valor_desconto double precision,
    ent_valor_outras_despesas double precision,
    ent_valor_ipi double precision,
    ent_total_produtos double precision,
    ent_total_nota double precision,
    ent_fpgto_codigo_id integer,
    ent_cpgto_codigo_id integer,
    ent_banc_codigo_id integer,
    ent_par_codigo_id integer,
    ent_usuario character varying(20),
    ent_usuario_alt character varying(20),
    ent_observacoes character varying(255)
);


ALTER TABLE public.entrada_notas OWNER TO postgres;

--
-- Name: entrada_notas_ent_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE entrada_notas_ent_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.entrada_notas_ent_codigo_id_seq OWNER TO postgres;

--
-- Name: entrada_notas_ent_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE entrada_notas_ent_codigo_id_seq OWNED BY entrada_notas.ent_codigo_id;


--
-- Name: entrada_notas_ent_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('entrada_notas_ent_codigo_id_seq', 0, true);


--
-- Name: forma_de_pagamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE forma_de_pagamento (
    fpgto_codigo_id integer NOT NULL,
    fpgto_descricao character varying(60)
);


ALTER TABLE public.forma_de_pagamento OWNER TO postgres;

--
-- Name: forma_de_pagamento_fpgto_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE forma_de_pagamento_fpgto_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forma_de_pagamento_fpgto_codigo_id_seq OWNER TO postgres;

--
-- Name: forma_de_pagamento_fpgto_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE forma_de_pagamento_fpgto_codigo_id_seq OWNED BY forma_de_pagamento.fpgto_codigo_id;


--
-- Name: forma_de_pagamento_fpgto_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('forma_de_pagamento_fpgto_codigo_id_seq', 0, true);


--
-- Name: funcionarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE funcionarios (
    func_codigo_id integer NOT NULL,
    func_nome character varying(60),
    func_depa_codigo_id integer,
    func_cargo character varying(60),
    func_cpf_cnpj character varying(18),
    func_telefone character varying(13),
    func_celular character varying(13),
    func_ativo character(3),
    func_comissao double precision,
    func_pessoa character varying(10)
);


ALTER TABLE public.funcionarios OWNER TO postgres;

--
-- Name: funcionarios_func_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE funcionarios_func_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionarios_func_codigo_id_seq OWNER TO postgres;

--
-- Name: funcionarios_func_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE funcionarios_func_codigo_id_seq OWNED BY funcionarios.func_codigo_id;


--
-- Name: funcionarios_func_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('funcionarios_func_codigo_id_seq', 0, true);


--
-- Name: grupos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grupos (
    grup_codigo_id integer NOT NULL,
    grup_descricao character varying(60),
    grup_tipo character varying(10)
);


ALTER TABLE public.grupos OWNER TO postgres;

--
-- Name: grupos_grup_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grupos_grup_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grupos_grup_codigo_id_seq OWNER TO postgres;

--
-- Name: grupos_grup_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grupos_grup_codigo_id_seq OWNED BY grupos.grup_codigo_id;


--
-- Name: grupos_grup_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupos_grup_codigo_id_seq', 0, true);


--
-- Name: item_apuracao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_apuracao (
    item_codigo_id integer NOT NULL,
    item_cot_codigo_id integer,
    item_par_codigo_id integer,
    item_prod_codigo_id integer,
    item_quantidade double precision,
    item_unitario double precision,
    item_total double precision
);


ALTER TABLE public.item_apuracao OWNER TO postgres;

--
-- Name: item_apuracao_item_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_apuracao_item_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_apuracao_item_codigo_id_seq OWNER TO postgres;

--
-- Name: item_apuracao_item_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_apuracao_item_codigo_id_seq OWNED BY item_apuracao.item_codigo_id;


--
-- Name: item_apuracao_item_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_apuracao_item_codigo_id_seq', 0, true);


--
-- Name: item_cotacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_cotacao (
    item_codigo_id integer NOT NULL,
    item_cot_codigo_id integer,
    item_prod_codigo_id integer,
    item_unitario double precision,
    item_quantidade double precision,
    item_total double precision,
    item_par_codigo_id integer,
    item_frete double precision
);


ALTER TABLE public.item_cotacao OWNER TO postgres;

--
-- Name: item_cotacao_item_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_cotacao_item_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_cotacao_item_codigo_id_seq OWNER TO postgres;

--
-- Name: item_cotacao_item_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_cotacao_item_codigo_id_seq OWNED BY item_cotacao.item_codigo_id;


--
-- Name: item_cotacao_item_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_cotacao_item_codigo_id_seq', 0, true);


--
-- Name: item_entrada; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_entrada (
    item_ent_codigo_id integer NOT NULL,
    item_not_codigo_id integer,
    item_ent_prod_codigo_id integer,
    item_ent_quantidade double precision,
    item_ent_percentual_desconto double precision,
    item_ent_cfop character varying(5),
    item_ent_icms double precision,
    item_ent_ipi double precision,
    item_ent_icms_sub_trib double precision,
    item_ent_valor_unitario double precision,
    item_ent_valor_total double precision,
    item_ent_base_calculo_icms double precision,
    item_ent_aliquota_icms double precision,
    item_ent_aliquota_ipi double precision,
    item_ent_peso_liquido double precision,
    item_ent_peso_bruto double precision,
    item_ent_cst character varying(3),
    item_ent_lote character varying(20)
);


ALTER TABLE public.item_entrada OWNER TO postgres;

--
-- Name: item_entrada_interna; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_entrada_interna (
    item_codigo_id integer NOT NULL,
    item_ent_codigo_id integer,
    item_prod_codigo_id integer,
    item_lote character varying(20),
    item_quantidade double precision
);


ALTER TABLE public.item_entrada_interna OWNER TO postgres;

--
-- Name: item_entrada_interna_item_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_entrada_interna_item_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_entrada_interna_item_codigo_id_seq OWNER TO postgres;

--
-- Name: item_entrada_interna_item_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_entrada_interna_item_codigo_id_seq OWNED BY item_entrada_interna.item_codigo_id;


--
-- Name: item_entrada_interna_item_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_entrada_interna_item_codigo_id_seq', 0, true);


--
-- Name: item_entrada_item_ent_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_entrada_item_ent_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_entrada_item_ent_codigo_id_seq OWNER TO postgres;

--
-- Name: item_entrada_item_ent_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_entrada_item_ent_codigo_id_seq OWNED BY item_entrada.item_ent_codigo_id;


--
-- Name: item_entrada_item_ent_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_entrada_item_ent_codigo_id_seq', 0, true);


--
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_pedido (
    item_ped_codigo_id integer NOT NULL,
    item_ped_pedido_codigo_id integer,
    item_ped_prod_codigo_id integer,
    item_ped_quantidade double precision,
    item_ped_percentual_desconto double precision,
    item_ped_cfop character varying(5),
    item_ped_icms double precision,
    item_ped_ipi double precision,
    item_ped_icms_sub_trib double precision,
    item_ped_valor_unitario double precision,
    item_ped_valor_total double precision,
    item_pedido_base_calculo_icms double precision,
    item_ped_aliquota_icms double precision,
    item_ped_aliquota_ipi double precision,
    item_ped_peso_liquido double precision,
    item_ped_peso_bruto double precision,
    item_ped_cst character varying(3),
    item_pedido_lote character varying(20)
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- Name: item_pedido_item_ped_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_pedido_item_ped_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_pedido_item_ped_codigo_id_seq OWNER TO postgres;

--
-- Name: item_pedido_item_ped_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_pedido_item_ped_codigo_id_seq OWNED BY item_pedido.item_ped_codigo_id;


--
-- Name: item_pedido_item_ped_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_pedido_item_ped_codigo_id_seq', 0, true);


--
-- Name: item_saida_interna; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_saida_interna (
    item_codigo_id integer NOT NULL,
    item_sai_codigo_id integer,
    item_prod_codigo_id integer,
    item_lote character varying(20),
    item_quantidade double precision
);


ALTER TABLE public.item_saida_interna OWNER TO postgres;

--
-- Name: item_saida_interna_item_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_saida_interna_item_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_saida_interna_item_codigo_id_seq OWNER TO postgres;

--
-- Name: item_saida_interna_item_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_saida_interna_item_codigo_id_seq OWNED BY item_saida_interna.item_codigo_id;


--
-- Name: item_saida_interna_item_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_saida_interna_item_codigo_id_seq', 0, true);


--
-- Name: item_solicitacao_compras; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_solicitacao_compras (
    item_codigo_id integer NOT NULL,
    item_com_codigo_id integer,
    item_prod_codigo_id integer,
    item_lote character varying(20),
    item_quantidade double precision
);


ALTER TABLE public.item_solicitacao_compras OWNER TO postgres;

--
-- Name: item_solicitacao_compras_item_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_solicitacao_compras_item_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_solicitacao_compras_item_codigo_id_seq OWNER TO postgres;

--
-- Name: item_solicitacao_compras_item_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_solicitacao_compras_item_codigo_id_seq OWNED BY item_solicitacao_compras.item_codigo_id;


--
-- Name: item_solicitacao_compras_item_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_solicitacao_compras_item_codigo_id_seq', 0, true);


--
-- Name: lotes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lotes (
    lot_codigo_id integer NOT NULL,
    lot_prod_codigo_id integer,
    lot_numero_lot character varying(20),
    lot_qtde double precision
);


ALTER TABLE public.lotes OWNER TO postgres;

--
-- Name: lotes_lot_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lotes_lot_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lotes_lot_codigo_id_seq OWNER TO postgres;

--
-- Name: lotes_lot_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lotes_lot_codigo_id_seq OWNED BY lotes.lot_codigo_id;


--
-- Name: lotes_lot_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lotes_lot_codigo_id_seq', 0, true);


--
-- Name: mensagem_fiscal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mensagem_fiscal (
    msg_codigo_id integer NOT NULL,
    msg_descricao character varying(255)
);


ALTER TABLE public.mensagem_fiscal OWNER TO postgres;

--
-- Name: mensagem_fiscal_msg_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mensagem_fiscal_msg_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mensagem_fiscal_msg_codigo_id_seq OWNER TO postgres;

--
-- Name: mensagem_fiscal_msg_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mensagem_fiscal_msg_codigo_id_seq OWNED BY mensagem_fiscal.msg_codigo_id;


--
-- Name: mensagem_fiscal_msg_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('mensagem_fiscal_msg_codigo_id_seq', 0, true);


--
-- Name: nota_fiscal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nota_fiscal (
    not_codigo_id integer NOT NULL,
    not_serie character varying(2),
    not_emissao date,
    not_saida date,
    not_tipo character varying(30),
    not_incorparar_frete character varying(3),
    not_incorporar_ipi character varying(3),
    not_par_codigo_id integer,
    not_tra_codigo_id integer,
    not_placa_transportadora character varying(8),
    not_responsavel_frete character varying(15),
    not_quantidade_tranportadora double precision,
    not_especie character varying(20),
    not_peso_bruto double precision,
    not_peso_liquido double precision,
    not_chave_nfe character varying(70),
    not_protocolo_nfe character varying(30),
    not_situacao_nfe character varying(120),
    not_base_icms double precision,
    not_valor_icms double precision,
    not_base_icms_sub double precision,
    not_valor_icms_sub double precision,
    not_valor_frete double precision,
    not_outras_despesas double precision,
    not_total_ipi double precision,
    not_pis double precision,
    not_cofins double precision,
    not_total_produtos double precision,
    not_total double precision,
    not_ped_codigo_id integer,
    not_informacoes_complementares text,
    not_xml text,
    not_numero_recebimento character varying(20),
    not_lote integer,
    not_xml_cancelamento text,
    not_data_cancelamento character varying(10)
);


ALTER TABLE public.nota_fiscal OWNER TO postgres;

--
-- Name: nota_fiscal_not_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE nota_fiscal_not_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nota_fiscal_not_codigo_id_seq OWNER TO postgres;

--
-- Name: nota_fiscal_not_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE nota_fiscal_not_codigo_id_seq OWNED BY nota_fiscal.not_codigo_id;


--
-- Name: nota_fiscal_not_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('nota_fiscal_not_codigo_id_seq', 0, true);


--
-- Name: parceiros; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE parceiros (
    par_codigo_id integer NOT NULL,
    par_tipo_parceiro character varying(10),
    par_nome_razao_social character varying(60),
    par_fantasia character varying(60),
    par_cnpj_cpf character varying(18),
    par_insc_estadual character varying(20),
    par_grup_codigo_id integer,
    par_ramo_codigo_id integer,
    par_limite_credito double precision,
    par_saldo_credito double precision,
    par_e_mail character varying(50),
    par_home_page character varying(50),
    par_data_cadastro date,
    par_observacao character varying(255),
    par_ativo character varying(3),
    par_rua_comercial character varying(255),
    par_numero_comercial integer,
    par_bairro_comercial character varying(255),
    par_complemento_comercial character varying(255),
    par_cidade_comercial_codigo_id integer,
    par_cep_comercial character varying(9),
    par_contato_comercial character varying(60),
    par_telefone_comercial character varying(13),
    par_fax_comercial character varying(13),
    par_pessoa character varying(10),
    par_isento_subs_tributaria character varying(3),
    par_isento_ipi character varying(3),
    par_isento_suframa character varying(3),
    par_data_nascimento date,
    par_rg character varying(12),
    par_matricula integer,
	par_ramal character varying(10),
	par_estadocivil character varying(12)
);


ALTER TABLE public.parceiros OWNER TO postgres;

--
-- Name: parceiros_par_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE parceiros_par_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.parceiros_par_codigo_id_seq OWNER TO postgres;

--
-- Name: parceiros_par_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE parceiros_par_codigo_id_seq OWNED BY parceiros.par_codigo_id;


--
-- Name: parceiros_par_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('parceiros_par_codigo_id_seq', 0, true);


--
-- Name: pedidos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedidos (
    ped_codigo_id integer NOT NULL,
    ped_par_codigo_id integer,
    ped_numero_nota_fiscal integer,
    ped_chave_nfe character varying(70),
    ped_par_end_codigo_id_entrega integer,
    ped_par_end_codigo_id_cobranca integer,
    ped_fpgto_codigo_id integer,
    ped_cpgto_codigo_id integer,
    ped_tipo_pedido character varying(20),
    ped_data_emissao date,
    ped_data_faturamento date,
    ped_valor_desconto double precision,
    ped_percentual_desconto double precision,
    ped_total_pedido double precision,
    ped_observacao character varying(255),
    ped_emp_codigo_id integer,
    ped_func_codigo_id integer,
    ped_banc_codigo_id integer,
    ped_oc_cliente character varying(20)
);


ALTER TABLE public.pedidos OWNER TO postgres;

--
-- Name: pedidos_ped_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pedidos_ped_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedidos_ped_codigo_id_seq OWNER TO postgres;

--
-- Name: pedidos_ped_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pedidos_ped_codigo_id_seq OWNED BY pedidos.ped_codigo_id;


--
-- Name: pedidos_ped_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pedidos_ped_codigo_id_seq', 0, true);


--
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE permissao (
    per_codigo_id integer NOT NULL,
    per_usu_codigo_id integer,
    per_tela character varying(60),
    per_grupo character varying(30),
    _001 boolean,
    _002 boolean,
    _003 boolean,
    _004 boolean,
    _005 boolean,
    _006 boolean,
    _007 boolean,
    _008 boolean,
    _009 boolean,
    _010 boolean,
    _011 boolean,
    _012 boolean,
    _013 boolean,
    _014 boolean,
    _015 boolean
);


ALTER TABLE public.permissao OWNER TO postgres;

--
-- Name: permissao_per_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE permissao_per_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permissao_per_codigo_id_seq OWNER TO postgres;

--
-- Name: permissao_per_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE permissao_per_codigo_id_seq OWNED BY permissao.per_codigo_id;


--
-- Name: permissao_per_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permissao_per_codigo_id_seq', 0, true);


--
-- Name: produtos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produtos (
    prod_codigo_id integer NOT NULL,
    prod_codigo_produto character varying(15),
    prod_descricao character varying(150),
    prod_codigo_ean character varying(80),
    prod_unid_codigo_id integer,
    prod_peso_liquido double precision,
    prod_peso_bruto double precision,
    prod_estoque_atual double precision,
    prod_estoque_minimo double precision,
    prod_estoque_maximo double precision,
    prod_preco_venda double precision,
    prod_valor_minimo_venda double precision,
    prod_grup_codigo_id integer,
    prod_trib_codigo_id integer,
    prod_reducao_icms double precision,
    prod_pis double precision,
    prod_cofins double precision,
    prod_aliq_diferenciada_icms double precision,
    prod_aliq_de_ipi double precision,
    prod_imagem_produto character varying(100),
    prod_observacao character varying(255),
    prod_ativo character varying(3),
    prod_icms double precision,
    prod_ncm character varying(10),
    prod_descricao_generica character varying(60),
    prod_cst character varying(3),
    prod_almox_codigo_id integer
);


ALTER TABLE public.produtos OWNER TO postgres;

--
-- Name: produtos_prod_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produtos_prod_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produtos_prod_codigo_id_seq OWNER TO postgres;

--
-- Name: produtos_prod_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produtos_prod_codigo_id_seq OWNED BY produtos.prod_codigo_id;


--
-- Name: produtos_prod_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produtos_prod_codigo_id_seq', 0, true);


--
-- Name: ramo_de_atividade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ramo_de_atividade (
    ramo_codigo_id integer NOT NULL,
    ramo_descricao character varying(60)
);


ALTER TABLE public.ramo_de_atividade OWNER TO postgres;

--
-- Name: ramo_de_atividade_ramo_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ramo_de_atividade_ramo_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ramo_de_atividade_ramo_codigo_id_seq OWNER TO postgres;

--
-- Name: ramo_de_atividade_ramo_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ramo_de_atividade_ramo_codigo_id_seq OWNED BY ramo_de_atividade.ramo_codigo_id;


--
-- Name: ramo_de_atividade_ramo_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ramo_de_atividade_ramo_codigo_id_seq', 0, true);


--
-- Name: recebimento_contas_pagar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE recebimento_contas_pagar (
    rcp_codigo_id integer NOT NULL,
    rcp_cp_codigo_id integer,
    rcp_data_pagamento date,
    rcp_usuario character varying(20),
    rcp_valor_pago double precision,
    rcp_acrescimo double precision,
    rcp_desconto double precision
);


ALTER TABLE public.recebimento_contas_pagar OWNER TO postgres;

--
-- Name: recebimento_contas_pagar_rcp_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE recebimento_contas_pagar_rcp_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recebimento_contas_pagar_rcp_codigo_id_seq OWNER TO postgres;

--
-- Name: recebimento_contas_pagar_rcp_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE recebimento_contas_pagar_rcp_codigo_id_seq OWNED BY recebimento_contas_pagar.rcp_codigo_id;


--
-- Name: recebimento_contas_pagar_rcp_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recebimento_contas_pagar_rcp_codigo_id_seq', 0, true);


--
-- Name: recebimento_contas_receber; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE recebimento_contas_receber (
    rcr_codigo_id integer NOT NULL,
    rcr_cr_codigo_id integer,
    rcr_data_recebimento date,
    rcr_usuario character varying(20),
    rcr_valor_recebido double precision,
    rcr_acrescimo double precision,
    rcr_desconto double precision
);


ALTER TABLE public.recebimento_contas_receber OWNER TO postgres;

--
-- Name: recebimento_contas_receber_rcr_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE recebimento_contas_receber_rcr_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recebimento_contas_receber_rcr_codigo_id_seq OWNER TO postgres;

--
-- Name: recebimento_contas_receber_rcr_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE recebimento_contas_receber_rcr_codigo_id_seq OWNED BY recebimento_contas_receber.rcr_codigo_id;


--
-- Name: recebimento_contas_receber_rcr_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recebimento_contas_receber_rcr_codigo_id_seq', 0, true);


--
-- Name: saida_interna; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE saida_interna (
    sai_codigo_id integer NOT NULL,
    sai_emp_codigo_id integer,
    sai_par_codigo_id integer,
    sai_data date,
    sai_controle_interno character varying(20),
    sai_observacao character varying(255),
    sai_usuario character varying(50)
);


ALTER TABLE public.saida_interna OWNER TO postgres;

--
-- Name: saida_interna_sai_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE saida_interna_sai_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.saida_interna_sai_codigo_id_seq OWNER TO postgres;

--
-- Name: saida_interna_sai_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE saida_interna_sai_codigo_id_seq OWNED BY saida_interna.sai_codigo_id;


--
-- Name: saida_interna_sai_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('saida_interna_sai_codigo_id_seq', 0, true);


--
-- Name: solicitacao_compras; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE solicitacao_compras (
    com_codigo_id integer NOT NULL,
    com_emp_codigo_id integer,
    com_func_codigo_id integer,
    com_data date,
    com_observacao character varying(255),
    com_usuario character varying(50),
    com_numero_solicitacao integer,
    com_situacao character varying(30)
);


ALTER TABLE public.solicitacao_compras OWNER TO postgres;

--
-- Name: solicitacao_compras_com_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE solicitacao_compras_com_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.solicitacao_compras_com_codigo_id_seq OWNER TO postgres;

--
-- Name: solicitacao_compras_com_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE solicitacao_compras_com_codigo_id_seq OWNED BY solicitacao_compras.com_codigo_id;


--
-- Name: solicitacao_compras_com_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('solicitacao_compras_com_codigo_id_seq', 0, true);


--
-- Name: tipo_documento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_documento (
    doc_codigo_id integer NOT NULL,
    doc_descricao character varying(60)
);


ALTER TABLE public.tipo_documento OWNER TO postgres;

--
-- Name: tipo_documento_doc_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_documento_doc_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_documento_doc_codigo_id_seq OWNER TO postgres;

--
-- Name: tipo_documento_doc_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_documento_doc_codigo_id_seq OWNED BY tipo_documento.doc_codigo_id;


--
-- Name: tipo_documento_doc_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipo_documento_doc_codigo_id_seq', 0, true);


--
-- Name: transportadoras; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE transportadoras (
    tran_codigo_id integer NOT NULL,
    tran_nome_razao_social character varying(60),
    tran_nome_fantasia character varying(60),
    tran_cpf_cnpj character varying(18),
    tran_insc_estadual character varying(11),
    tran_logradouro character varying(60),
    tran_numero integer,
    tran_bairro character varying(60),
    tran_complemento character varying(40),
    tran_cep character varying(9),
    tran_cid_codigo_id integer,
    tran_telefone character varying(13),
    tran_fax character varying(13),
    tran_contato character varying(40),
    tran_licenca character varying(20),
    tran_validade_licenca date,
    tran_pessoa character varying(10)
);


ALTER TABLE public.transportadoras OWNER TO postgres;

--
-- Name: transportadoras_tran_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE transportadoras_tran_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transportadoras_tran_codigo_id_seq OWNER TO postgres;

--
-- Name: transportadoras_tran_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE transportadoras_tran_codigo_id_seq OWNED BY transportadoras.tran_codigo_id;


--
-- Name: transportadoras_tran_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transportadoras_tran_codigo_id_seq', 0, true);


--
-- Name: tributos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tributos (
    trib_codigo_id integer NOT NULL,
    trib_descricao character varying(60),
    trib_classificacao_fiscal character varying(2),
    trib_codigo_fiscal integer,
    trib_cst character varying(3)
);


ALTER TABLE public.tributos OWNER TO postgres;

--
-- Name: tributos_trib_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tributos_trib_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tributos_trib_codigo_id_seq OWNER TO postgres;

--
-- Name: tributos_trib_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tributos_trib_codigo_id_seq OWNED BY tributos.trib_codigo_id;


--
-- Name: tributos_trib_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tributos_trib_codigo_id_seq', 0, false);


--
-- Name: unidade_de_medida; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE unidade_de_medida (
    unid_codigo_id integer NOT NULL,
    unid_descricao character varying(60),
    unid_unidade character varying(3)
);


ALTER TABLE public.unidade_de_medida OWNER TO postgres;

--
-- Name: unidade_de_medida_unid_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE unidade_de_medida_unid_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.unidade_de_medida_unid_codigo_id_seq OWNER TO postgres;

--
-- Name: unidade_de_medida_unid_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE unidade_de_medida_unid_codigo_id_seq OWNED BY unidade_de_medida.unid_codigo_id;


--
-- Name: unidade_de_medida_unid_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('unidade_de_medida_unid_codigo_id_seq', 0, true);


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarios (
    usu_codigo_id integer NOT NULL,
    usu_nome character varying(20),
    usu_senha character varying(10),
    usu_ativo character varying(3),
    usu_super character varying(3)
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuarios_usu_codigo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_usu_codigo_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 10000000000
    CACHE 1;


ALTER TABLE public.usuarios_usu_codigo_id_seq OWNER TO postgres;

--
-- Name: usuarios_usu_codigo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuarios_usu_codigo_id_seq OWNED BY usuarios.usu_codigo_id;


--
-- Name: usuarios_usu_codigo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuarios_usu_codigo_id_seq', 0, true);


--
-- Name: almox_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY almoxarifados ALTER COLUMN almox_codigo_id SET DEFAULT nextval('almoxarifados_almox_codigo_id_seq'::regclass);


--
-- Name: custo_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY centro_custo ALTER COLUMN custo_codigo_id SET DEFAULT nextval('centro_custo_custo_codigo_id_seq'::regclass);


--
-- Name: cfop_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cfop ALTER COLUMN cfop_codigo_id SET DEFAULT nextval('cfop_cfop_codigo_id_seq'::regclass);


--
-- Name: cid_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidades ALTER COLUMN cid_codigo_id SET DEFAULT nextval('cidades_cid_codigo_id_seq'::regclass);


--
-- Name: cpgto_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY condicao_pagamento ALTER COLUMN cpgto_codigo_id SET DEFAULT nextval('condicao_pagamento_cpgto_codigo_id_seq'::regclass);


--
-- Name: banc_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contas_bancarias ALTER COLUMN banc_codigo_id SET DEFAULT nextval('contas_bancarias_banc_codigo_id_seq'::regclass);


--
-- Name: cc_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contas_corrente ALTER COLUMN cc_codigo_id SET DEFAULT nextval('contas_corrente_cc_codigo_id_seq'::regclass);


--
-- Name: cp_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contas_pagar ALTER COLUMN cp_codigo_id SET DEFAULT nextval('contas_pagar_cp_codigo_id_seq'::regclass);


--
-- Name: cr_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contas_receber ALTER COLUMN cr_codigo_id SET DEFAULT nextval('contas_receber_cr_codigo_id_seq'::regclass);


--
-- Name: cot_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cotacao ALTER COLUMN cot_codigo_id SET DEFAULT nextval('cotacao_cot_codigo_id_seq'::regclass);


--
-- Name: depa_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY departamento ALTER COLUMN depa_codigo_id SET DEFAULT nextval('departamento_depa_codigo_id_seq'::regclass);


--
-- Name: emp_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresas ALTER COLUMN emp_codigo_id SET DEFAULT nextval('empresas_emp_codigo_id_seq'::regclass);


--
-- Name: ent_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entrada_interna ALTER COLUMN ent_codigo_id SET DEFAULT nextval('entrada_interna_ent_codigo_id_seq'::regclass);


--
-- Name: ent_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entrada_notas ALTER COLUMN ent_codigo_id SET DEFAULT nextval('entrada_notas_ent_codigo_id_seq'::regclass);


--
-- Name: fpgto_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY forma_de_pagamento ALTER COLUMN fpgto_codigo_id SET DEFAULT nextval('forma_de_pagamento_fpgto_codigo_id_seq'::regclass);


--
-- Name: func_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionarios ALTER COLUMN func_codigo_id SET DEFAULT nextval('funcionarios_func_codigo_id_seq'::regclass);


--
-- Name: grup_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupos ALTER COLUMN grup_codigo_id SET DEFAULT nextval('grupos_grup_codigo_id_seq'::regclass);


--
-- Name: item_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_apuracao ALTER COLUMN item_codigo_id SET DEFAULT nextval('item_apuracao_item_codigo_id_seq'::regclass);


--
-- Name: item_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_cotacao ALTER COLUMN item_codigo_id SET DEFAULT nextval('item_cotacao_item_codigo_id_seq'::regclass);


--
-- Name: item_ent_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_entrada ALTER COLUMN item_ent_codigo_id SET DEFAULT nextval('item_entrada_item_ent_codigo_id_seq'::regclass);


--
-- Name: item_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_entrada_interna ALTER COLUMN item_codigo_id SET DEFAULT nextval('item_entrada_interna_item_codigo_id_seq'::regclass);


--
-- Name: item_ped_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_pedido ALTER COLUMN item_ped_codigo_id SET DEFAULT nextval('item_pedido_item_ped_codigo_id_seq'::regclass);


--
-- Name: item_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_saida_interna ALTER COLUMN item_codigo_id SET DEFAULT nextval('item_saida_interna_item_codigo_id_seq'::regclass);


--
-- Name: item_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_solicitacao_compras ALTER COLUMN item_codigo_id SET DEFAULT nextval('item_solicitacao_compras_item_codigo_id_seq'::regclass);


--
-- Name: lot_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lotes ALTER COLUMN lot_codigo_id SET DEFAULT nextval('lotes_lot_codigo_id_seq'::regclass);


--
-- Name: msg_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem_fiscal ALTER COLUMN msg_codigo_id SET DEFAULT nextval('mensagem_fiscal_msg_codigo_id_seq'::regclass);


--
-- Name: not_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nota_fiscal ALTER COLUMN not_codigo_id SET DEFAULT nextval('nota_fiscal_not_codigo_id_seq'::regclass);


--
-- Name: par_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parceiros ALTER COLUMN par_codigo_id SET DEFAULT nextval('parceiros_par_codigo_id_seq'::regclass);


--
-- Name: ped_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidos ALTER COLUMN ped_codigo_id SET DEFAULT nextval('pedidos_ped_codigo_id_seq'::regclass);


--
-- Name: per_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissao ALTER COLUMN per_codigo_id SET DEFAULT nextval('permissao_per_codigo_id_seq'::regclass);


--
-- Name: prod_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produtos ALTER COLUMN prod_codigo_id SET DEFAULT nextval('produtos_prod_codigo_id_seq'::regclass);


--
-- Name: ramo_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ramo_de_atividade ALTER COLUMN ramo_codigo_id SET DEFAULT nextval('ramo_de_atividade_ramo_codigo_id_seq'::regclass);


--
-- Name: rcp_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY recebimento_contas_pagar ALTER COLUMN rcp_codigo_id SET DEFAULT nextval('recebimento_contas_pagar_rcp_codigo_id_seq'::regclass);


--
-- Name: rcr_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY recebimento_contas_receber ALTER COLUMN rcr_codigo_id SET DEFAULT nextval('recebimento_contas_receber_rcr_codigo_id_seq'::regclass);


--
-- Name: sai_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY saida_interna ALTER COLUMN sai_codigo_id SET DEFAULT nextval('saida_interna_sai_codigo_id_seq'::regclass);


--
-- Name: com_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY solicitacao_compras ALTER COLUMN com_codigo_id SET DEFAULT nextval('solicitacao_compras_com_codigo_id_seq'::regclass);


--
-- Name: doc_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento ALTER COLUMN doc_codigo_id SET DEFAULT nextval('tipo_documento_doc_codigo_id_seq'::regclass);


--
-- Name: tran_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transportadoras ALTER COLUMN tran_codigo_id SET DEFAULT nextval('transportadoras_tran_codigo_id_seq'::regclass);


--
-- Name: trib_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tributos ALTER COLUMN trib_codigo_id SET DEFAULT nextval('tributos_trib_codigo_id_seq'::regclass);


--
-- Name: unid_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY unidade_de_medida ALTER COLUMN unid_codigo_id SET DEFAULT nextval('unidade_de_medida_unid_codigo_id_seq'::regclass);


--
-- Name: usu_codigo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios ALTER COLUMN usu_codigo_id SET DEFAULT nextval('usuarios_usu_codigo_id_seq'::regclass);


--
-- Name: 16966; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('16966');


ALTER LARGE OBJECT 16966 OWNER TO postgres;

--
-- Data for Name: almoxarifados; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY almoxarifados (almox_codigo_id, almox_descricao) FROM stdin;
--\.


--
-- Data for Name: centro_custo; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY centro_custo (custo_codigo_id, custo_centro_custo, custo_descricao) FROM stdin;
--\.


--
-- Data for Name: cfop; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY cfop (cfop_codigo_id, cfop_cfop, cfop_descricao, cfop_msg1_codigo_id, cfop_msg2_codigo_id, cfop_msg3_codigo_id, cfop_msg4_codigo_id, --cfop_incorporar_ipi) FROM stdin;
--1	5.102	VENDA DE MERCADORIA	\N	\N	\N	\N	\N
--2	6.102	VENDAS FORA ESTADO	\N	\N	\N	\N	\N
--3	1.102	COMPRA PARA COMERCIALIZAÇÃO 	\N	\N	\N	\N	\N
--\.


--
-- Data for Name: cidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidades (cid_codigo_id, cid_codigo_uf, cid_uf, cid_codigo_municipio, cid_municipio) FROM stdin;
1	11	RO	1100015	ALTA FLORESTA D OESTE
2	11	RO	1100023	ARIQUEMES
3	11	RO	1100031	CABIXI
4	11	RO	1100049	CACOAL
5	11	RO	1100056	CEREJEIRAS
6	11	RO	1100064	COLORADO DO OESTE
7	11	RO	1100072	CORUMBIARA
8	11	RO	1100080	COSTA MARQUES
9	11	RO	1100098	ESPIGÃO D OESTE
10	11	RO	1100106	GUAJARÁ-MIRIM
11	11	RO	1100114	JARU
12	11	RO	1100122	JI-PARANÁ
13	11	RO	1100130	MACHADINHO D OESTE
14	11	RO	1100148	NOVA BRASILÂNDIA D OESTE
15	11	RO	1100155	OURO PRETO DO OESTE
16	11	RO	1100189	PIMENTA BUENO
17	11	RO	1100205	PORTO VELHO
18	11	RO	1100254	PRESIDENTE MÉDICI
19	11	RO	1100262	RIO CRESPO
20	11	RO	1100288	ROLIM DE MOURA
21	11	RO	1100296	SANTA LUZIA D OESTE
22	11	RO	1100304	VILHENA
23	11	RO	1100320	SÃO MIGUEL DO GUAPORÉ
24	11	RO	1100338	NOVA MAMORÉ
25	11	RO	1100346	ALVORADA D OESTE
26	11	RO	1100379	ALTO ALEGRE DOS PARECIS
27	11	RO	1100403	ALTO PARAÍSO
28	11	RO	1100452	BURITIS
29	11	RO	1100502	NOVO HORIZONTE DO OESTE
30	11	RO	1100601	CACAULÂNDIA
31	11	RO	1100700	CAMPO NOVO DE RONDÔNIA
32	11	RO	1100809	CANDEIAS DO JAMARI
33	11	RO	1100908	CASTANHEIRAS
34	11	RO	1100924	CHUPINGUAIA
35	11	RO	1100940	CUJUBIM
36	11	RO	1101005	GOVERNADOR JORGE TEIXEIRA
37	11	RO	1101104	ITAPUÃ DO OESTE
38	11	RO	1101203	MINISTRO ANDREAZZA
39	11	RO	1101302	MIRANTE DA SERRA
40	11	RO	1101401	MONTE NEGRO
41	11	RO	1101435	NOVA UNIÃO
42	11	RO	1101450	PARECIS
43	11	RO	1101468	PIMENTEIRAS DO OESTE
44	11	RO	1101476	PRIMAVERA DE RONDÔNIA
45	11	RO	1101484	SÃO FELIPE D OESTE
46	11	RO	1101492	SÃO FRANCISCO DO GUAPORÉ
47	11	RO	1101500	SERINGUEIRAS
48	11	RO	1101559	TEIXEIRÓPOLIS
49	11	RO	1101609	THEOBROMA
50	11	RO	1101708	URUPÁ
51	11	RO	1101757	VALE DO ANARI
52	11	RO	1101807	VALE DO PARAÍSO
53	12	AC	1200013	ACRELÂNDIA
54	12	AC	1200054	ASSIS BRASIL
55	12	AC	1200104	BRASILÉIA
56	12	AC	1200138	BUJARI
57	12	AC	1200179	CAPIXABA
58	12	AC	1200203	CRUZEIRO DO SUL
59	12	AC	1200252	EPITACIOLÂNDIA
60	12	AC	1200302	FEIJÓ
61	12	AC	1200328	JORDÃO
62	12	AC	1200336	MÂNCIO LIMA
63	12	AC	1200344	MANOEL URBANO
64	12	AC	1200351	MARECHAL THAUMATURGO
65	12	AC	1200385	PLÁCIDO DE CASTRO
66	12	AC	1200393	PORTO WALTER
67	12	AC	1200401	RIO BRANCO
68	12	AC	1200427	RODRIGUES ALVES
69	12	AC	1200435	SANTA ROSA DO PURUS
70	12	AC	1200450	SENADOR GUIOMARD
71	12	AC	1200500	SENA MADUREIRA
72	12	AC	1200609	TARAUACÁ
73	12	AC	1200708	XAPURI
74	12	AC	1200807	PORTO ACRE
75	13	AM	1300029	ALVARÃES
76	13	AM	1300060	AMATURÁ
77	13	AM	1300086	ANAMÃ
78	13	AM	1300102	ANORI
79	13	AM	1300144	APUÍ
80	13	AM	1300201	ATALAIA DO NORTE
81	13	AM	1300300	AUTAZES
82	13	AM	1300409	BARCELOS
83	13	AM	1300508	BARREIRINHA
84	13	AM	1300607	BENJAMIN CONSTANT
85	13	AM	1300631	BERURI
86	13	AM	1300680	BOA VISTA DO RAMOS
87	13	AM	1300706	BOCA DO ACRE
88	13	AM	1300805	BORBA
89	13	AM	1300839	CAAPIRANGA
90	13	AM	1300904	CANUTAMA
91	13	AM	1301001	CARAUARI
92	13	AM	1301100	CAREIRO
93	13	AM	1301159	CAREIRO DA VÁRZEA
94	13	AM	1301209	COARI
95	13	AM	1301308	CODAJÁS
96	13	AM	1301407	EIRUNEPÉ
97	13	AM	1301506	ENVIRA
98	13	AM	1301605	FONTE BOA
99	13	AM	1301654	GUAJARÁ
100	13	AM	1301704	HUMAITÁ
101	13	AM	1301803	IPIXUNA
102	13	AM	1301852	IRANDUBA
103	13	AM	1301902	ITACOATIARA
104	13	AM	1301951	ITAMARATI
105	13	AM	1302009	ITAPIRANGA
106	13	AM	1302108	JAPURÁ
107	13	AM	1302207	JURUÁ
108	13	AM	1302306	JUTAÍ
109	13	AM	1302405	LÁBREA
110	13	AM	1302504	MANACAPURU
111	13	AM	1302553	MANAQUIRI
112	13	AM	1302603	MANAUS
113	13	AM	1302702	MANICORÉ
114	13	AM	1302801	MARAÃ
115	13	AM	1302900	MAUÉS
116	13	AM	1303007	NHAMUNDÁ
117	13	AM	1303106	NOVA OLINDA DO NORTE
118	13	AM	1303205	NOVO AIRÃO
119	13	AM	1303304	NOVO ARIPUANÃ
120	13	AM	1303403	PARINTINS
121	13	AM	1303502	PAUINI
122	13	AM	1303536	PRESIDENTE FIGUEIREDO
123	13	AM	1303569	RIO PRETO DA EVA
124	13	AM	1303601	SANTA ISABEL DO RIO NEGRO
125	13	AM	1303700	SANTO ANTÔNIO DO IÇÁ
126	13	AM	1303809	SÃO GABRIEL DA CACHOEIRA
127	13	AM	1303908	SÃO PAULO DE OLIVENÇA
128	13	AM	1303957	SÃO SEBASTIÃO DO UATUMÃ
129	13	AM	1304005	SILVES
130	13	AM	1304062	TABATINGA
131	13	AM	1304104	TAPAUÁ
132	13	AM	1304203	TEFÉ
133	13	AM	1304237	TONANTINS
134	13	AM	1304260	UARINI
135	13	AM	1304302	URUCARÁ
136	13	AM	1304401	URUCURITUBA
137	14	RR	1400027	AMAJARI
138	14	RR	1400050	ALTO ALEGRE
139	14	RR	1400100	BOA VISTA
140	14	RR	1400159	BONFIM
141	14	RR	1400175	CANTÁ
142	14	RR	1400209	CARACARAÍ
143	14	RR	1400233	CAROEBE
144	14	RR	1400282	IRACEMA
145	14	RR	1400308	MUCAJAÍ
146	14	RR	1400407	NORMANDIA
147	14	RR	1400456	PACARAIMA
148	14	RR	1400472	RORAINÓPOLIS
149	14	RR	1400506	SÃO JOÃO DA BALIZA
150	14	RR	1400605	SÃO LUIZ
151	14	RR	1400704	UIRAMUTÃ
152	15	PA	1500107	ABAETETUBA
153	15	PA	1500131	ABEL FIGUEIREDO
154	15	PA	1500206	ACARÁ
155	15	PA	1500305	AFUÁ
156	15	PA	1500347	ÁGUA AZUL DO NORTE
157	15	PA	1500404	ALENQUER
158	15	PA	1500503	ALMEIRIM
159	15	PA	1500602	ALTAMIRA
160	15	PA	1500701	ANAJÁS
161	15	PA	1500800	ANANINDEUA
162	15	PA	1500859	ANAPU
163	15	PA	1500909	AUGUSTO CORRÊA
164	15	PA	1500958	AURORA DO PARÁ
165	15	PA	1501006	AVEIRO
166	15	PA	1501105	BAGRE
167	15	PA	1501204	BAIÃO
168	15	PA	1501253	BANNACH
169	15	PA	1501303	BARCARENA
170	15	PA	1501402	BELÉM
171	15	PA	1501451	BELTERRA
172	15	PA	1501501	BENEVIDES
173	15	PA	1501576	BOM JESUS DO TOCANTINS
174	15	PA	1501600	BONITO
175	15	PA	1501709	BRAGANÇA
176	15	PA	1501725	BRASIL NOVO
177	15	PA	1501758	BREJO GRANDE DO ARAGUAIA
178	15	PA	1501782	BREU BRANCO
179	15	PA	1501808	BREVES
180	15	PA	1501907	BUJARU
181	15	PA	1501956	CACHOEIRA DO PIRIÁ
182	15	PA	1502004	CACHOEIRA DO ARARI
183	15	PA	1502103	CAMETÁ
184	15	PA	1502152	CANAÃ DOS CARAJÁS
185	15	PA	1502202	CAPANEMA
186	15	PA	1502301	CAPITÃO POÇO
187	15	PA	1502400	CASTANHAL
188	15	PA	1502509	CHAVES
189	15	PA	1502608	COLARES
190	15	PA	1502707	CONCEIÇÃO DO ARAGUAIA
191	15	PA	1502756	CONCÓRDIA DO PARÁ
192	15	PA	1502764	CUMARU DO NORTE
193	15	PA	1502772	CURIONÓPOLIS
194	15	PA	1502806	CURRALINHO
195	15	PA	1502855	CURUÁ
196	15	PA	1502905	CURUÇÁ
197	15	PA	1502939	DOM ELISEU
198	15	PA	1502954	ELDORADO DOS CARAJÁS
199	15	PA	1503002	FARO
200	15	PA	1503044	FLORESTA DO ARAGUAIA
201	15	PA	1503077	GARRAFÃO DO NORTE
202	15	PA	1503093	GOIANÉSIA DO PARÁ
203	15	PA	1503101	GURUPÁ
204	15	PA	1503200	IGARAPÉ-AÇU
205	15	PA	1503309	IGARAPÉ-MIRI
206	15	PA	1503408	INHANGAPI
207	15	PA	1503457	IPIXUNA DO PARÁ
208	15	PA	1503507	IRITUIA
209	15	PA	1503606	ITAITUBA
210	15	PA	1503705	ITUPIRANGA
211	15	PA	1503754	JACAREACANGA
212	15	PA	1503804	JACUNDÁ
213	15	PA	1503903	JURUTI
214	15	PA	1504000	LIMOEIRO DO AJURU
215	15	PA	1504059	MÃE DO RIO
216	15	PA	1504109	MAGALHÃES BARATA
217	15	PA	1504208	MARABÁ
218	15	PA	1504307	MARACANÃ
219	15	PA	1504406	MARAPANIM
220	15	PA	1504422	MARITUBA
221	15	PA	1504455	MEDICILÂNDIA
222	15	PA	1504505	MELGAÇO
223	15	PA	1504604	MOCAJUBA
224	15	PA	1504703	MOJU
225	15	PA	1504802	MONTE ALEGRE
226	15	PA	1504901	MUANÁ
227	15	PA	1504950	NOVA ESPERANÇA DO PIRIÁ
228	15	PA	1504976	NOVA IPIXUNA
229	15	PA	1505007	NOVA TIMBOTEUA
230	15	PA	1505031	NOVO PROGRESSO
231	15	PA	1505064	NOVO REPARTIMENTO
232	15	PA	1505106	ÓBIDOS
233	15	PA	1505205	OEIRAS DO PARÁ
234	15	PA	1505304	ORIXIMINÁ
235	15	PA	1505403	OURÉM
236	15	PA	1505437	OURILÂNDIA DO NORTE
237	15	PA	1505486	PACAJÁ
238	15	PA	1505494	PALESTINA DO PARÁ
239	15	PA	1505502	PARAGOMINAS
240	15	PA	1505536	PARAUAPEBAS
241	15	PA	1505551	PAU D ARCO
242	15	PA	1505601	PEIXE-BOI
243	15	PA	1505635	PIÇARRA
244	15	PA	1505650	PLACAS
245	15	PA	1505700	PONTA DE PEDRAS
246	15	PA	1505809	PORTEL
247	15	PA	1505908	PORTO DE MOZ
248	15	PA	1506005	PRAINHA
249	15	PA	1506104	PRIMAVERA
250	15	PA	1506112	QUATIPURU
251	15	PA	1506138	REDENÇÃO
252	15	PA	1506161	RIO MARIA
253	15	PA	1506187	RONDON DO PARÁ
254	15	PA	1506195	RURÓPOLIS
255	15	PA	1506203	SALINÓPOLIS
256	15	PA	1506302	SALVATERRA
257	15	PA	1506351	SANTA BÁRBARA DO PARÁ
258	15	PA	1506401	SANTA CRUZ DO ARARI
259	15	PA	1506500	SANTA ISABEL DO PARÁ
260	15	PA	1506559	SANTA LUZIA DO PARÁ
261	15	PA	1506583	SANTA MARIA DAS BARREIRAS
262	15	PA	1506609	SANTA MARIA DO PARÁ
263	15	PA	1506708	SANTANA DO ARAGUAIA
264	15	PA	1506807	SANTARÉM
265	15	PA	1506906	SANTARÉM NOVO
266	15	PA	1507003	SANTO ANTÔNIO DO TAUÁ
267	15	PA	1507102	SÃO CAETANO DE ODIVELAS
268	15	PA	1507151	SÃO DOMINGOS DO ARAGUAIA
269	15	PA	1507201	SÃO DOMINGOS DO CAPIM
270	15	PA	1507300	SÃO FÉLIX DO XINGU
271	15	PA	1507409	SÃO FRANCISCO DO PARÁ
272	15	PA	1507458	SÃO GERALDO DO ARAGUAIA
273	15	PA	1507466	SÃO JOÃO DA PONTA
274	15	PA	1507474	SÃO JOÃO DE PIRABAS
275	15	PA	1507508	SÃO JOÃO DO ARAGUAIA
276	15	PA	1507607	SÃO MIGUEL DO GUAMÁ
277	15	PA	1507706	SÃO SEBASTIÃO DA BOA VISTA
278	15	PA	1507755	SAPUCAIA
279	15	PA	1507805	SENADOR JOSÉ PORFÍRIO
280	15	PA	1507904	SOURE
281	15	PA	1507953	TAILÂNDIA
282	15	PA	1507961	TERRA ALTA
283	15	PA	1507979	TERRA SANTA
284	15	PA	1508001	TOMÉ-AÇU
285	15	PA	1508035	TRACUATEUA
286	15	PA	1508050	TRAIRÃO
287	15	PA	1508084	TUCUMÃ
288	15	PA	1508100	TUCURUÍ
289	15	PA	1508126	ULIANÓPOLIS
290	15	PA	1508159	URUARÁ
291	15	PA	1508209	VIGIA
292	15	PA	1508308	VISEU
293	15	PA	1508357	VITÓRIA DO XINGU
294	15	PA	1508407	XINGUARA
295	16	AP	1600055	SERRA DO NAVIO
296	16	AP	1600105	AMAPÁ
297	16	AP	1600154	PEDRA BRANCA DO AMAPARÍ
298	16	AP	1600204	CALÇOENE
299	16	AP	1600212	CUTIAS
300	16	AP	1600238	FERREIRA GOMES
301	16	AP	1600253	ITAUBAL
302	16	AP	1600279	LARANJAL DO JARI
303	16	AP	1600303	MACAPÁ
304	16	AP	1600402	MAZAGÃO
305	16	AP	1600501	OIAPOQUE
306	16	AP	1600535	PORTO GRANDE
307	16	AP	1600550	PRACUÚBA
308	16	AP	1600600	SANTANA
309	16	AP	1600709	TARTARUGALZINHO
310	16	AP	1600808	VITÓRIA DO JARI
311	17	TO	1700251	ABREULÂNDIA
312	17	TO	1700301	AGUIARNÓPOLIS
313	17	TO	1700350	ALIANÇA DO TOCANTINS
314	17	TO	1700400	ALMAS
315	17	TO	1700707	ALVORADA
316	17	TO	1701002	ANANÁS
317	17	TO	1701051	ANGICO
318	17	TO	1701101	APARECIDA DO RIO NEGRO
319	17	TO	1701309	ARAGOMINAS
320	17	TO	1701903	ARAGUACEMA
321	17	TO	1702000	ARAGUAÇU
322	17	TO	1702109	ARAGUAÍNA
323	17	TO	1702158	ARAGUANÃ
324	17	TO	1702208	ARAGUATINS
325	17	TO	1702307	ARAPOEMA
326	17	TO	1702406	ARRAIAS
327	17	TO	1702554	AUGUSTINÓPOLIS
328	17	TO	1702703	AURORA DO TOCANTINS
329	17	TO	1702901	AXIXÁ DO TOCANTINS
330	17	TO	1703008	BABAÇULÂNDIA
331	17	TO	1703057	BANDEIRANTES DO TOCANTINS
332	17	TO	1703073	BARRA DO OURO
333	17	TO	1703107	BARROLÂNDIA
334	17	TO	1703206	BERNARDO SAYÃO
335	17	TO	1703305	BOM JESUS DO TOCANTINS
336	17	TO	1703602	BRASILÂNDIA DO TOCANTINS
337	17	TO	1703701	BREJINHO DE NAZARÉ
338	17	TO	1703800	BURITI DO TOCANTINS
339	17	TO	1703826	CACHOEIRINHA
340	17	TO	1703842	CAMPOS LINDOS
341	17	TO	1703867	CARIRI DO TOCANTINS
342	17	TO	1703883	CARMOLÂNDIA
343	17	TO	1703891	CARRASCO BONITO
344	17	TO	1703909	CASEARA
345	17	TO	1704105	CENTENÁRIO
346	17	TO	1704600	CHAPADA DE AREIA
347	17	TO	1705102	CHAPADA DA NATIVIDADE
348	17	TO	1705508	COLINAS DO TOCANTINS
349	17	TO	1705557	COMBINADO
350	17	TO	1705607	CONCEIÇÃO DO TOCANTINS
351	17	TO	1706001	COUTO DE MAGALHÃES
352	17	TO	1706100	CRISTALÂNDIA
353	17	TO	1706258	CRIXÁS DO TOCANTINS
354	17	TO	1706506	DARCINÓPOLIS
355	17	TO	1707009	DIANÓPOLIS
356	17	TO	1707108	DIVINÓPOLIS DO TOCANTINS
357	17	TO	1707207	DOIS IRMÃOS DO TOCANTINS
358	17	TO	1707306	DUERÉ
359	17	TO	1707405	ESPERANTINA
360	17	TO	1707553	FÁTIMA
361	17	TO	1707652	FIGUEIRÓPOLIS
362	17	TO	1707702	FILADÉLFIA
363	17	TO	1708205	FORMOSO DO ARAGUAIA
364	17	TO	1708254	FORTALEZA DO TABOCÃO
365	17	TO	1708304	GOIANORTE
366	17	TO	1709005	GOIATINS
367	17	TO	1709302	GUARAÍ
368	17	TO	1709500	GURUPI
369	17	TO	1709807	IPUEIRAS
370	17	TO	1710508	ITACAJÁ
371	17	TO	1710706	ITAGUATINS
372	17	TO	1710904	ITAPIRATINS
373	17	TO	1711100	ITAPORÃ DO TOCANTINS
374	17	TO	1711506	JAÚ DO TOCANTINS
375	17	TO	1711803	JUARINA
376	17	TO	1711902	LAGOA DA CONFUSÃO
377	17	TO	1711951	LAGOA DO TOCANTINS
378	17	TO	1712009	LAJEADO
379	17	TO	1712157	LAVANDEIRA
380	17	TO	1712405	LIZARDA
381	17	TO	1712454	LUZINÓPOLIS
382	17	TO	1712504	MARIANÓPOLIS DO TOCANTINS
383	17	TO	1712702	MATEIROS
384	17	TO	1712801	MAURILÂNDIA DO TOCANTINS
385	17	TO	1713205	MIRACEMA DO TOCANTINS
386	17	TO	1713304	MIRANORTE
387	17	TO	1713601	MONTE DO CARMO
388	17	TO	1713700	MONTE SANTO DO TOCANTINS
389	17	TO	1713809	PALMEIRAS DO TOCANTINS
390	17	TO	1713957	MURICILÂNDIA
391	17	TO	1714203	NATIVIDADE
392	17	TO	1714302	NAZARÉ
393	17	TO	1714880	NOVA OLINDA
394	17	TO	1715002	NOVA ROSALÂNDIA
395	17	TO	1715101	NOVO ACORDO
396	17	TO	1715150	NOVO ALEGRE
397	17	TO	1715259	NOVO JARDIM
398	17	TO	1715507	OLIVEIRA DE FÁTIMA
399	17	TO	1715705	PALMEIRANTE
400	17	TO	1715754	PALMEIRÓPOLIS
401	17	TO	1716109	PARAÍSO DO TOCANTINS
402	17	TO	1716208	PARANÃ
403	17	TO	1716307	PAU D ARCO
404	17	TO	1716505	PEDRO AFONSO
405	17	TO	1716604	PEIXE
406	17	TO	1716653	PEQUIZEIRO
407	17	TO	1716703	COLMÉIA
408	17	TO	1717008	PINDORAMA DO TOCANTINS
409	17	TO	1717206	PIRAQUÊ
410	17	TO	1717503	PIUM
411	17	TO	1717800	PONTE ALTA DO BOM JESUS
412	17	TO	1717909	PONTE ALTA DO TOCANTINS
413	17	TO	1718006	PORTO ALEGRE DO TOCANTINS
414	17	TO	1718204	PORTO NACIONAL
415	17	TO	1718303	PRAIA NORTE
416	17	TO	1718402	PRESIDENTE KENNEDY
417	17	TO	1718451	PUGMIL
418	17	TO	1718501	RECURSOLÂNDIA
419	17	TO	1718550	RIACHINHO
420	17	TO	1718659	RIO DA CONCEIÇÃO
421	17	TO	1718709	RIO DOS BOIS
422	17	TO	1718758	RIO SONO
423	17	TO	1718808	SAMPAIO
424	17	TO	1718840	SANDOLÂNDIA
425	17	TO	1718865	SANTA FÉ DO ARAGUAIA
426	17	TO	1718881	SANTA MARIA DO TOCANTINS
427	17	TO	1718899	SANTA RITA DO TOCANTINS
428	17	TO	1718907	SANTA ROSA DO TOCANTINS
429	17	TO	1719004	SANTA TEREZA DO TOCANTINS
430	17	TO	1720002	SANTA TEREZINHA DO TOCANTINS
431	17	TO	1720101	SÃO BENTO DO TOCANTINS
432	17	TO	1720150	SÃO FÉLIX DO TOCANTINS
433	17	TO	1720200	SÃO MIGUEL DO TOCANTINS
434	17	TO	1720259	SÃO SALVADOR DO TOCANTINS
435	17	TO	1720309	SÃO SEBASTIÃO DO TOCANTINS
436	17	TO	1720499	SÃO VALÉRIO DA NATIVIDADE
437	17	TO	1720655	SILVANÓPOLIS
438	17	TO	1720804	SÍTIO NOVO DO TOCANTINS
439	17	TO	1720853	SUCUPIRA
440	17	TO	1720903	TAGUATINGA
441	17	TO	1720937	TAIPAS DO TOCANTINS
442	17	TO	1720978	TALISMÃ
443	17	TO	1721000	PALMAS
444	17	TO	1721109	TOCANTÍNIA
445	17	TO	1721208	TOCANTINÓPOLIS
446	17	TO	1721257	TUPIRAMA
447	17	TO	1721307	TUPIRATINS
448	17	TO	1722081	WANDERLÂNDIA
449	17	TO	1722107	XAMBIOÁ
450	21	MA	2100055	AÇAILÂNDIA
451	21	MA	2100105	AFONSO CUNHA
452	21	MA	2100154	ÁGUA DOCE DO MARANHÃO
453	21	MA	2100204	ALCÂNTARA
454	21	MA	2100303	ALDEIAS ALTAS
455	21	MA	2100402	ALTAMIRA DO MARANHÃO
456	21	MA	2100436	ALTO ALEGRE DO MARANHÃO
457	21	MA	2100477	ALTO ALEGRE DO PINDARÉ
458	21	MA	2100501	ALTO PARNAÍBA
459	21	MA	2100550	AMAPÁ DO MARANHÃO
460	21	MA	2100600	AMARANTE DO MARANHÃO
461	21	MA	2100709	ANAJATUBA
462	21	MA	2100808	ANAPURUS
463	21	MA	2100832	APICUM-AÇU
464	21	MA	2100873	ARAGUANÃ
465	21	MA	2100907	ARAIOSES
466	21	MA	2100956	ARAME
467	21	MA	2101004	ARARI
468	21	MA	2101103	AXIXÁ
469	21	MA	2101202	BACABAL
470	21	MA	2101251	BACABEIRA
471	21	MA	2101301	BACURI
472	21	MA	2101350	BACURITUBA
473	21	MA	2101400	BALSAS
474	21	MA	2101509	BARÃO DE GRAJAÚ
475	21	MA	2101608	BARRA DO CORDA
476	21	MA	2101707	BARREIRINHAS
477	21	MA	2101731	BELÁGUA
478	21	MA	2101772	BELA VISTA DO MARANHÃO
479	21	MA	2101806	BENEDITO LEITE
480	21	MA	2101905	BEQUIMÃO
481	21	MA	2101939	BERNARDO DO MEARIM
482	21	MA	2101970	BOA VISTA DO GURUPI
483	21	MA	2102002	BOM JARDIM
484	21	MA	2102036	BOM JESUS DAS SELVAS
485	21	MA	2102077	BOM LUGAR
486	21	MA	2102101	BREJO
487	21	MA	2102150	BREJO DE AREIA
488	21	MA	2102200	BURITI
489	21	MA	2102309	BURITI BRAVO
490	21	MA	2102325	BURITICUPU
491	21	MA	2102358	BURITIRANA
492	21	MA	2102374	CACHOEIRA GRANDE
493	21	MA	2102408	CAJAPIÓ
494	21	MA	2102507	CAJARI
495	21	MA	2102556	CAMPESTRE DO MARANHÃO
496	21	MA	2102606	CÂNDIDO MENDES
497	21	MA	2102705	CANTANHEDE
498	21	MA	2102754	CAPINZAL DO NORTE
499	21	MA	2102804	CAROLINA
500	21	MA	2102903	CARUTAPERA
501	21	MA	2103000	CAXIAS
502	21	MA	2103109	CEDRAL
503	21	MA	2103125	CENTRAL DO MARANHÃO
504	21	MA	2103158	CENTRO DO GUILHERME
505	21	MA	2103174	CENTRO NOVO DO MARANHÃO
506	21	MA	2103208	CHAPADINHA
507	21	MA	2103257	CIDELÂNDIA
508	21	MA	2103307	CODÓ
509	21	MA	2103406	COELHO NETO
510	21	MA	2103505	COLINAS
511	21	MA	2103554	CONCEIÇÃO DO LAGO-AÇU
512	21	MA	2103604	COROATÁ
513	21	MA	2103703	CURURUPU
514	21	MA	2103752	DAVINÓPOLIS
515	21	MA	2103802	DOM PEDRO
516	21	MA	2103901	DUQUE BACELAR
517	21	MA	2104008	ESPERANTINÓPOLIS
518	21	MA	2104057	ESTREITO
519	21	MA	2104073	FEIRA NOVA DO MARANHÃO
520	21	MA	2104081	FERNANDO FALCÃO
521	21	MA	2104099	FORMOSA DA SERRA NEGRA
522	21	MA	2104107	FORTALEZA DOS NOGUEIRAS
523	21	MA	2104206	FORTUNA
524	21	MA	2104305	GODOFREDO VIANA
525	21	MA	2104404	GONÇALVES DIAS
526	21	MA	2104503	GOVERNADOR ARCHER
527	21	MA	2104552	GOVERNADOR EDISON LOBÃO
528	21	MA	2104602	GOVERNADOR EUGÊNIO BARROS
529	21	MA	2104628	GOVERNADOR LUIZ ROCHA
530	21	MA	2104651	GOVERNADOR NEWTON BELLO
531	21	MA	2104677	GOVERNADOR NUNES FREIRE
532	21	MA	2104701	GRAÇA ARANHA
533	21	MA	2104800	GRAJAÚ
534	21	MA	2104909	GUIMARÃES
535	21	MA	2105005	HUMBERTO DE CAMPOS
536	21	MA	2105104	ICATU
537	21	MA	2105153	IGARAPÉ DO MEIO
538	21	MA	2105203	IGARAPÉ GRANDE
539	21	MA	2105302	IMPERATRIZ
540	21	MA	2105351	ITAIPAVA DO GRAJAÚ
541	21	MA	2105401	ITAPECURU MIRIM
542	21	MA	2105427	ITINGA DO MARANHÃO
543	21	MA	2105450	JATOBÁ
544	21	MA	2105476	JENIPAPO DOS VIEIRAS
545	21	MA	2105500	JOÃO LISBOA
546	21	MA	2105609	JOSELÂNDIA
547	21	MA	2105658	JUNCO DO MARANHÃO
548	21	MA	2105708	LAGO DA PEDRA
549	21	MA	2105807	LAGO DO JUNCO
550	21	MA	2105906	LAGO VERDE
551	21	MA	2105922	LAGOA DO MATO
552	21	MA	2105948	LAGO DOS RODRIGUES
553	21	MA	2105963	LAGOA GRANDE DO MARANHÃO
554	21	MA	2105989	LAJEADO NOVO
555	21	MA	2106003	LIMA CAMPOS
556	21	MA	2106102	LORETO
557	21	MA	2106201	LUÍS DOMINGUES
558	21	MA	2106300	MAGALHÃES DE ALMEIDA
559	21	MA	2106326	MARACAÇUMÉ
560	21	MA	2106359	MARAJÁ DO SENA
561	21	MA	2106375	MARANHÃOZINHO
562	21	MA	2106409	MATA ROMA
563	21	MA	2106508	MATINHA
564	21	MA	2106607	MATÕES
565	21	MA	2106631	MATÕES DO NORTE
566	21	MA	2106672	MILAGRES DO MARANHÃO
567	21	MA	2106706	MIRADOR
568	21	MA	2106755	MIRANDA DO NORTE
569	21	MA	2106805	MIRINZAL
570	21	MA	2106904	MONÇÃO
571	21	MA	2107001	MONTES ALTOS
572	21	MA	2107100	MORROS
573	21	MA	2107209	NINA RODRIGUES
574	21	MA	2107258	NOVA COLINAS
575	21	MA	2107308	NOVA IORQUE
576	21	MA	2107357	NOVA OLINDA DO MARANHÃO
577	21	MA	2107407	OLHO D ÁGUA DAS CUNHÃS
578	21	MA	2107456	OLINDA NOVA DO MARANHÃO
579	21	MA	2107506	PAÇO DO LUMIAR
580	21	MA	2107605	PALMEIRÂNDIA
581	21	MA	2107704	PARAIBANO
582	21	MA	2107803	PARNARAMA
583	21	MA	2107902	PASSAGEM FRANCA
584	21	MA	2108009	PASTOS BONS
585	21	MA	2108058	PAULINO NEVES
586	21	MA	2108108	PAULO RAMOS
587	21	MA	2108207	PEDREIRAS
588	21	MA	2108256	PEDRO DO ROSÁRIO
589	21	MA	2108306	PENALVA
590	21	MA	2108405	PERI MIRIM
591	21	MA	2108454	PERITORÓ
592	21	MA	2108504	PINDARÉ-MIRIM
593	21	MA	2108603	PINHEIRO
594	21	MA	2108702	PIO XII
595	21	MA	2108801	PIRAPEMAS
596	21	MA	2108900	POÇÃO DE PEDRAS
597	21	MA	2109007	PORTO FRANCO
598	21	MA	2109056	PORTO RICO DO MARANHÃO
599	21	MA	2109106	PRESIDENTE DUTRA
600	21	MA	2109205	PRESIDENTE JUSCELINO
601	21	MA	2109239	PRESIDENTE MÉDICI
602	21	MA	2109270	PRESIDENTE SARNEY
603	21	MA	2109304	PRESIDENTE VARGAS
604	21	MA	2109403	PRIMEIRA CRUZ
605	21	MA	2109452	RAPOSA
606	21	MA	2109502	RIACHÃO
607	21	MA	2109551	RIBAMAR FIQUENE
608	21	MA	2109601	ROSÁRIO
609	21	MA	2109700	SAMBAÍBA
610	21	MA	2109759	SANTA FILOMENA DO MARANHÃO
611	21	MA	2109809	SANTA HELENA
612	21	MA	2109908	SANTA INÊS
613	21	MA	2110005	SANTA LUZIA
614	21	MA	2110039	SANTA LUZIA DO PARUÁ
615	21	MA	2110104	SANTA QUITÉRIA DO MARANHÃO
616	21	MA	2110203	SANTA RITA
617	21	MA	2110237	SANTANA DO MARANHÃO
618	21	MA	2110278	SANTO AMARO DO MARANHÃO
619	21	MA	2110302	SANTO ANTÔNIO DOS LOPES
620	21	MA	2110401	SÃO BENEDITO DO RIO PRETO
621	21	MA	2110500	SÃO BENTO
622	21	MA	2110609	SÃO BERNARDO
623	21	MA	2110658	SÃO DOMINGOS DO AZEITÃO
624	21	MA	2110708	SÃO DOMINGOS DO MARANHÃO
625	21	MA	2110807	SÃO FÉLIX DE BALSAS
626	21	MA	2110856	SÃO FRANCISCO DO BREJÃO
627	21	MA	2110906	SÃO FRANCISCO DO MARANHÃO
628	21	MA	2111003	SÃO JOÃO BATISTA
629	21	MA	2111029	SÃO JOÃO DO CARÚ
630	21	MA	2111052	SÃO JOÃO DO PARAÍSO
631	21	MA	2111078	SÃO JOÃO DO SOTER
632	21	MA	2111102	SÃO JOÃO DOS PATOS
633	21	MA	2111201	SÃO JOSÉ DE RIBAMAR
634	21	MA	2111250	SÃO JOSÉ DOS BASÍLIOS
635	21	MA	2111300	SÃO LUÍS
636	21	MA	2111409	SÃO LUÍS GONZAGA DO MARANHÃO
637	21	MA	2111508	SÃO MATEUS DO MARANHÃO
638	21	MA	2111532	SÃO PEDRO DA ÁGUA BRANCA
639	21	MA	2111573	SÃO PEDRO DOS CRENTES
640	21	MA	2111607	SÃO RAIMUNDO DAS MANGABEIRAS
641	21	MA	2111631	SÃO RAIMUNDO DO DOCA BEZERRA
642	21	MA	2111672	SÃO ROBERTO
643	21	MA	2111706	SÃO VICENTE FERRER
644	21	MA	2111722	SATUBINHA
645	21	MA	2111748	SENADOR ALEXANDRE COSTA
646	21	MA	2111763	SENADOR LA ROCQUE
647	21	MA	2111789	SERRANO DO MARANHÃO
648	21	MA	2111805	SÍTIO NOVO
649	21	MA	2111904	SUCUPIRA DO NORTE
650	21	MA	2111953	SUCUPIRA DO RIACHÃO
651	21	MA	2112001	TASSO FRAGOSO
652	21	MA	2112100	TIMBIRAS
653	21	MA	2112209	TIMON
654	21	MA	2112233	TRIZIDELA DO VALE
655	21	MA	2112274	TUFILÂNDIA
656	21	MA	2112308	TUNTUM
657	21	MA	2112407	TURIAÇU
658	21	MA	2112456	TURILÂNDIA
659	21	MA	2112506	TUTÓIA
660	21	MA	2112605	URBANO SANTOS
661	21	MA	2112704	VARGEM GRANDE
662	21	MA	2112803	VIANA
663	21	MA	2112852	VILA NOVA DOS MARTÍRIOS
664	21	MA	2112902	VITÓRIA DO MEARIM
665	21	MA	2113009	VITORINO FREIRE
666	21	MA	2114007	ZÉ DOCA
667	22	PI	2200053	ACAUÃ
668	22	PI	2200103	AGRICOLÂNDIA
669	22	PI	2200202	ÁGUA BRANCA
670	22	PI	2200251	ALAGOINHA DO PIAUÍ
671	22	PI	2200277	ALEGRETE DO PIAUÍ
672	22	PI	2200301	ALTO LONGÁ
673	22	PI	2200400	ALTOS
674	22	PI	2200459	ALVORADA DO GURGUÉIA
675	22	PI	2200509	AMARANTE
676	22	PI	2200608	ANGICAL DO PIAUÍ
677	22	PI	2200707	ANÍSIO DE ABREU
678	22	PI	2200806	ANTÔNIO ALMEIDA
679	22	PI	2200905	AROAZES
680	22	PI	2201002	ARRAIAL
681	22	PI	2201051	ASSUNÇÃO DO PIAUÍ
682	22	PI	2201101	AVELINO LOPES
683	22	PI	2201150	BAIXA GRANDE DO RIBEIRO
684	22	PI	2201176	BARRA D ALCÂNTARA
685	22	PI	2201200	BARRAS
686	22	PI	2201309	BARREIRAS DO PIAUÍ
687	22	PI	2201408	BARRO DURO
688	22	PI	2201507	BATALHA
689	22	PI	2201556	BELA VISTA DO PIAUÍ
690	22	PI	2201572	BELÉM DO PIAUÍ
691	22	PI	2201606	BENEDITINOS
692	22	PI	2201705	BERTOLÍNIA
693	22	PI	2201739	BETÂNIA DO PIAUÍ
694	22	PI	2201770	BOA HORA
695	22	PI	2201804	BOCAINA
696	22	PI	2201903	BOM JESUS
697	22	PI	2201919	BOM PRINCÍPIO DO PIAUÍ
698	22	PI	2201929	BONFIM DO PIAUÍ
699	22	PI	2201945	BOQUEIRÃO DO PIAUÍ
700	22	PI	2201960	BRASILEIRA
701	22	PI	2201988	BREJO DO PIAUÍ
702	22	PI	2202000	BURITI DOS LOPES
703	22	PI	2202026	BURITI DOS MONTES
704	22	PI	2202059	CABECEIRAS DO PIAUÍ
705	22	PI	2202075	CAJAZEIRAS DO PIAUÍ
706	22	PI	2202083	CAJUEIRO DA PRAIA
707	22	PI	2202091	CALDEIRÃO GRANDE DO PIAUÍ
708	22	PI	2202109	CAMPINAS DO PIAUÍ
709	22	PI	2202117	CAMPO ALEGRE DO FIDALGO
710	22	PI	2202133	CAMPO GRANDE DO PIAUÍ
711	22	PI	2202174	CAMPO LARGO DO PIAUÍ
712	22	PI	2202208	CAMPO MAIOR
713	22	PI	2202251	CANAVIEIRA
714	22	PI	2202307	CANTO DO BURITI
715	22	PI	2202406	CAPITÃO DE CAMPOS
716	22	PI	2202455	CAPITÃO GERVÁSIO OLIVEIRA
717	22	PI	2202505	CARACOL
718	22	PI	2202539	CARAÚBAS DO PIAUÍ
719	22	PI	2202554	CARIDADE DO PIAUÍ
720	22	PI	2202604	CASTELO DO PIAUÍ
721	22	PI	2202653	CAXINGÓ
722	22	PI	2202703	COCAL
723	22	PI	2202711	COCAL DE TELHA
724	22	PI	2202729	COCAL DOS ALVES
725	22	PI	2202737	COIVARAS
726	22	PI	2202752	COLÔNIA DO GURGUÉIA
727	22	PI	2202778	COLÔNIA DO PIAUÍ
728	22	PI	2202802	CONCEIÇÃO DO CANINDÉ
729	22	PI	2202851	CORONEL JOSÉ DIAS
730	22	PI	2202901	CORRENTE
731	22	PI	2203008	CRISTALÂNDIA DO PIAUÍ
732	22	PI	2203107	CRISTINO CASTRO
733	22	PI	2203206	CURIMATÁ
734	22	PI	2203230	CURRAIS
735	22	PI	2203255	CURRALINHOS
736	22	PI	2203271	CURRAL NOVO DO PIAUÍ
737	22	PI	2203305	DEMERVAL LOBÃO
738	22	PI	2203354	DIRCEU ARCOVERDE
739	22	PI	2203404	DOM EXPEDITO LOPES
740	22	PI	2203420	DOMINGOS MOURÃO
741	22	PI	2203453	DOM INOCÊNCIO
742	22	PI	2203503	ELESBÃO VELOSO
743	22	PI	2203602	ELISEU MARTINS
744	22	PI	2203701	ESPERANTINA
745	22	PI	2203750	FARTURA DO PIAUÍ
746	22	PI	2203800	FLORES DO PIAUÍ
747	22	PI	2203859	FLORESTA DO PIAUÍ
748	22	PI	2203909	FLORIANO
749	22	PI	2204006	FRANCINÓPOLIS
750	22	PI	2204105	FRANCISCO AYRES
751	22	PI	2204154	FRANCISCO MACEDO
752	22	PI	2204204	FRANCISCO SANTOS
753	22	PI	2204303	FRONTEIRAS
754	22	PI	2204352	GEMINIANO
755	22	PI	2204402	GILBUÉS
756	22	PI	2204501	GUADALUPE
757	22	PI	2204550	GUARIBAS
758	22	PI	2204600	HUGO NAPOLEÃO
759	22	PI	2204659	ILHA GRANDE
760	22	PI	2204709	INHUMA
761	22	PI	2204808	IPIRANGA DO PIAUÍ
762	22	PI	2204907	ISAÍAS COELHO
763	22	PI	2205003	ITAINÓPOLIS
764	22	PI	2205102	ITAUEIRA
765	22	PI	2205151	JACOBINA DO PIAUÍ
766	22	PI	2205201	JAICÓS
767	22	PI	2205250	JARDIM DO MULATO
768	22	PI	2205276	JATOBÁ DO PIAUÍ
769	22	PI	2205300	JERUMENHA
770	22	PI	2205359	JOÃO COSTA
771	22	PI	2205409	JOAQUIM PIRES
772	22	PI	2205458	JOCA MARQUES
773	22	PI	2205508	JOSÉ DE FREITAS
774	22	PI	2205516	JUAZEIRO DO PIAUÍ
775	22	PI	2205524	JÚLIO BORGES
776	22	PI	2205532	JUREMA
777	22	PI	2205540	LAGOINHA DO PIAUÍ
778	22	PI	2205557	LAGOA ALEGRE
779	22	PI	2205565	LAGOA DO BARRO DO PIAUÍ
780	22	PI	2205573	LAGOA DE SÃO FRANCISCO
781	22	PI	2205581	LAGOA DO PIAUÍ
782	22	PI	2205599	LAGOA DO SÍTIO
783	22	PI	2205607	LANDRI SALES
784	22	PI	2205706	LUÍS CORREIA
785	22	PI	2205805	LUZILÂNDIA
786	22	PI	2205854	MADEIRO
787	22	PI	2205904	MANOEL EMÍDIO
788	22	PI	2205953	MARCOLÂNDIA
789	22	PI	2206001	MARCOS PARENTE
790	22	PI	2206050	MASSAPÊ DO PIAUÍ
791	22	PI	2206100	MATIAS OLÍMPIO
792	22	PI	2206209	MIGUEL ALVES
793	22	PI	2206308	MIGUEL LEÃO
794	22	PI	2206357	MILTON BRANDÃO
795	22	PI	2206407	MONSENHOR GIL
796	22	PI	2206506	MONSENHOR HIPÓLITO
797	22	PI	2206605	MONTE ALEGRE DO PIAUÍ
798	22	PI	2206654	MORRO CABEÇA NO TEMPO
799	22	PI	2206670	MORRO DO CHAPÉU DO PIAUÍ
800	22	PI	2206696	MURICI DOS PORTELAS
801	22	PI	2206704	NAZARÉ DO PIAUÍ
802	22	PI	2206753	NOSSA SENHORA DE NAZARÉ
803	22	PI	2206803	NOSSA SENHORA DOS REMÉDIO
804	22	PI	2206902	NOVO ORIENTE DO PIAUÍ
805	22	PI	2206951	NOVO SANTO ANTÔNIO
806	22	PI	2207009	OEIRAS
807	22	PI	2207108	OLHO D ÁGUA DO PIAUÍ
808	22	PI	2207207	PADRE MARCOS
809	22	PI	2207306	PAES LANDIM
810	22	PI	2207355	PAJEÚ DO PIAUÍ
811	22	PI	2207405	PALMEIRA DO PIAUÍ
812	22	PI	2207504	PALMEIRAIS
813	22	PI	2207553	PAQUETÁ
814	22	PI	2207603	PARNAGUÁ
815	22	PI	2207702	PARNAÍBA
816	22	PI	2207751	PASSAGEM FRANCA DO PIAUÍ
817	22	PI	2207777	PATOS DO PIAUÍ
818	22	PI	2207793	PAU D ARCO DO PIAUÍ
819	22	PI	2207801	PAULISTANA
820	22	PI	2207850	PAVUSSU
821	22	PI	2207900	PEDRO II
822	22	PI	2207934	PEDRO LAURENTINO
823	22	PI	2207959	NOVA SANTA RITA
824	22	PI	2208007	PICOS
825	22	PI	2208106	PIMENTEIRAS
826	22	PI	2208205	PIO IX
827	22	PI	2208304	PIRACURUCA
828	22	PI	2208403	PIRIPIRI
829	22	PI	2208502	PORTO
830	22	PI	2208551	PORTO ALEGRE DO PIAUÍ
831	22	PI	2208601	PRATA DO PIAUÍ
832	22	PI	2208650	QUEIMADA NOVA
833	22	PI	2208700	REDENÇÃO DO GURGUÉIA
834	22	PI	2208809	REGENERAÇÃO
835	22	PI	2208858	RIACHO FRIO
836	22	PI	2208874	RIBEIRA DO PIAUÍ
837	22	PI	2208908	RIBEIRO GONÇALVES
838	22	PI	2209005	RIO GRANDE DO PIAUÍ
839	22	PI	2209104	SANTA CRUZ DO PIAUÍ
840	22	PI	2209153	SANTA CRUZ DOS MILAGRES
841	22	PI	2209203	SANTA FILOMENA
842	22	PI	2209302	SANTA LUZ
843	22	PI	2209351	SANTANA DO PIAUÍ
844	22	PI	2209377	SANTA ROSA DO PIAUÍ
845	22	PI	2209401	SANTO ANTÔNIO DE LISBOA
846	22	PI	2209450	SANTO ANTÔNIO DOS MILAGRES
847	22	PI	2209500	SANTO INÁCIO DO PIAUÍ
848	22	PI	2209559	SÃO BRAZ DO PIAUÍ
849	22	PI	2209609	SÃO FÉLIX DO PIAUÍ
850	22	PI	2209658	SÃO FRANCISCO DE ASSIS DO PIAUÍ
851	22	PI	2209708	SÃO FRANCISCO DO PIAUÍ
852	22	PI	2209757	SÃO GONÇALO DO GURGUÉIA
853	22	PI	2209807	SÃO GONÇALO DO PIAUÍ
854	22	PI	2209856	SÃO JOÃO DA CANABRAVA
855	22	PI	2209872	SÃO JOÃO DA FRONTEIRA
856	22	PI	2209906	SÃO JOÃO DA SERRA
857	22	PI	2209955	SÃO JOÃO DA VARJOTA
858	22	PI	2209971	SÃO JOÃO DO ARRAIAL
859	22	PI	2210003	SÃO JOÃO DO PIAUÍ
860	22	PI	2210052	SÃO JOSÉ DO DIVINO
861	22	PI	2210102	SÃO JOSÉ DO PEIXE
862	22	PI	2210201	SÃO JOSÉ DO PIAUÍ
863	22	PI	2210300	SÃO JULIÃO
864	22	PI	2210359	SÃO LOURENÇO DO PIAUÍ
865	22	PI	2210375	SÃO LUIS DO PIAUÍ
866	22	PI	2210383	SÃO MIGUEL DA BAIXA GRANDE
867	22	PI	2210391	SÃO MIGUEL DO FIDALGO
868	22	PI	2210409	SÃO MIGUEL DO TAPUIO
869	22	PI	2210508	SÃO PEDRO DO PIAUÍ
870	22	PI	2210607	SÃO RAIMUNDO NONATO
871	22	PI	2210623	SEBASTIÃO BARROS
872	22	PI	2210631	SEBASTIÃO LEAL
873	22	PI	2210656	SIGEFREDO PACHECO
874	22	PI	2210706	SIMÕES
875	22	PI	2210805	SIMPLÍCIO MENDES
876	22	PI	2210904	SOCORRO DO PIAUÍ
877	22	PI	2210938	SUSSUAPARA
878	22	PI	2210953	TAMBORIL DO PIAUÍ
879	22	PI	2210979	TANQUE DO PIAUÍ
880	22	PI	2211001	TERESINA
881	22	PI	2211100	UNIÃO
882	22	PI	2211209	URUÇUÍ
883	22	PI	2211308	VALENÇA DO PIAUÍ
884	22	PI	2211357	VÁRZEA BRANCA
885	22	PI	2211407	VÁRZEA GRANDE
886	22	PI	2211506	VERA MENDES
887	22	PI	2211605	VILA NOVA DO PIAUÍ
888	22	PI	2211704	WALL FERRAZ
889	23	CE	2300101	ABAIARA
890	23	CE	2300150	ACARAPÉ
891	23	CE	2300200	ACARAÚ
892	23	CE	2300309	ACOPIARA
893	23	CE	2300408	AIUABA
894	23	CE	2300507	ALCÂNTARAS
895	23	CE	2300606	ALTANEIRA
896	23	CE	2300705	ALTO SANTO
897	23	CE	2300754	AMONTADA
898	23	CE	2300804	ANTONINA DO NORTE
899	23	CE	2300903	APUIARÉS
900	23	CE	2301000	AQUIRAZ
901	23	CE	2301109	ARACATI
902	23	CE	2301208	ARACOIABA
903	23	CE	2301257	ARARENDÁ
904	23	CE	2301307	ARARIPE
905	23	CE	2301406	ARATUBA
906	23	CE	2301505	ARNEIROZ
907	23	CE	2301604	ASSARÉ
908	23	CE	2301703	AURORA
909	23	CE	2301802	BAIXIO
910	23	CE	2301851	BANABUIÚ
911	23	CE	2301901	BARBALHA
912	23	CE	2301950	BARREIRA
913	23	CE	2302008	BARRO
914	23	CE	2302057	BARROQUINHA
915	23	CE	2302107	BATURITÉ
916	23	CE	2302206	BEBERIBE
917	23	CE	2302305	BELA CRUZ
918	23	CE	2302404	BOA VIAGEM
919	23	CE	2302503	BREJO SANTO
920	23	CE	2302602	CAMOCIM
921	23	CE	2302701	CAMPOS SALES
922	23	CE	2302800	CANINDÉ
923	23	CE	2302909	CAPISTRANO
924	23	CE	2303006	CARIDADE
925	23	CE	2303105	CARIRÉ
926	23	CE	2303204	CARIRIAÇU
927	23	CE	2303303	CARIÚS
928	23	CE	2303402	CARNAUBAL
929	23	CE	2303501	CASCAVEL
930	23	CE	2303600	CATARINA
931	23	CE	2303659	CATUNDA
932	23	CE	2303709	CAUCAIA
933	23	CE	2303808	CEDRO
934	23	CE	2303907	CHAVAL
935	23	CE	2303931	CHORÓ
936	23	CE	2303956	CHOROZINHO
937	23	CE	2304004	COREAÚ
938	23	CE	2304103	CRATEÚS
939	23	CE	2304202	CRATO
940	23	CE	2304236	CROATÁ
941	23	CE	2304251	CRUZ
942	23	CE	2304269	DEPUTADO IRAPUAN PINHEIRO
943	23	CE	2304277	ERERÊ
944	23	CE	2304285	EUSÉBIO
945	23	CE	2304301	FARIAS BRITO
946	23	CE	2304350	FORQUILHA
947	23	CE	2304400	FORTALEZA
948	23	CE	2304459	FORTIM
949	23	CE	2304509	FRECHEIRINHA
950	23	CE	2304608	GENERAL SAMPAIO
951	23	CE	2304657	GRAÇA
952	23	CE	2304707	GRANJA
953	23	CE	2304806	GRANJEIRO
954	23	CE	2304905	GROAÍRAS
955	23	CE	2304954	GUAIÚBA
956	23	CE	2305001	GUARACIABA DO NORTE
957	23	CE	2305100	GUARAMIRANGA
958	23	CE	2305209	HIDROLÂNDIA
959	23	CE	2305233	HORIZONTE
960	23	CE	2305266	IBARETAMA
961	23	CE	2305308	IBIAPINA
962	23	CE	2305332	IBICUITINGA
963	23	CE	2305357	ICAPUÍ
964	23	CE	2305407	ICÓ
965	23	CE	2305506	IGUATU
966	23	CE	2305605	INDEPENDÊNCIA
967	23	CE	2305654	IPAPORANGA
968	23	CE	2305704	IPAUMIRIM
969	23	CE	2305803	IPU
970	23	CE	2305902	IPUEIRAS
971	23	CE	2306009	IRACEMA
972	23	CE	2306108	IRAUÇUBA
973	23	CE	2306207	ITAIÇABA
974	23	CE	2306256	ITAITINGA
975	23	CE	2306306	ITAPAGÉ
976	23	CE	2306405	ITAPIPOCA
977	23	CE	2306504	ITAPIÚNA
978	23	CE	2306553	ITAREMA
979	23	CE	2306603	ITATIRA
980	23	CE	2306702	JAGUARETAMA
981	23	CE	2306801	JAGUARIBARA
982	23	CE	2306900	JAGUARIBE
983	23	CE	2307007	JAGUARUANA
984	23	CE	2307106	JARDIM
985	23	CE	2307205	JATI
986	23	CE	2307254	JIJOCA DE JERICOACOARA
987	23	CE	2307304	JUAZEIRO DO NORTE
988	23	CE	2307403	JUCÁS
989	23	CE	2307502	LAVRAS DA MANGABEIRA
990	23	CE	2307601	LIMOEIRO DO NORTE
991	23	CE	2307635	MADALENA
992	23	CE	2307650	MARACANAÚ
993	23	CE	2307700	MARANGUAPE
994	23	CE	2307809	MARCO
995	23	CE	2307908	MARTINÓPOLE
996	23	CE	2308005	MASSAPÊ
997	23	CE	2308104	MAURITI
998	23	CE	2308203	MERUOCA
999	23	CE	2308302	MILAGRES
1000	23	CE	2308351	MILHÃ
1001	23	CE	2308377	MIRAÍMA
1002	23	CE	2308401	MISSÃO VELHA
1003	23	CE	2308500	MOMBAÇA
1004	23	CE	2308609	MONSENHOR TABOSA
1005	23	CE	2308708	MORADA NOVA
1006	23	CE	2308807	MORAÚJO
1007	23	CE	2308906	MORRINHOS
1008	23	CE	2309003	MUCAMBO
1009	23	CE	2309102	MULUNGU
1010	23	CE	2309201	NOVA OLINDA
1011	23	CE	2309300	NOVA RUSSAS
1012	23	CE	2309409	NOVO ORIENTE
1013	23	CE	2309458	OCARA
1014	23	CE	2309508	ORÓS
1015	23	CE	2309607	PACAJUS
1016	23	CE	2309706	PACATUBA
1017	23	CE	2309805	PACOTI
1018	23	CE	2309904	PACUJÁ
1019	23	CE	2310001	PALHANO
1020	23	CE	2310100	PALMÁCIA
1021	23	CE	2310209	PARACURU
1022	23	CE	2310258	PARAIPABA
1023	23	CE	2310308	PARAMBU
1024	23	CE	2310407	PARAMOTI
1025	23	CE	2310506	PEDRA BRANCA
1026	23	CE	2310605	PENAFORTE
1027	23	CE	2310704	PENTECOSTE
1028	23	CE	2310803	PEREIRO
1029	23	CE	2310852	PINDORETAMA
1030	23	CE	2310902	PIQUET CARNEIRO
1031	23	CE	2310951	PIRES FERREIRA
1032	23	CE	2311009	PORANGA
1033	23	CE	2311108	PORTEIRAS
1034	23	CE	2311207	POTENGI
1035	23	CE	2311231	POTIRETAMA
1036	23	CE	2311264	QUITERIANÓPOLIS
1037	23	CE	2311306	QUIXADÁ
1038	23	CE	2311355	QUIXELÔ
1039	23	CE	2311405	QUIXERAMOBIM
1040	23	CE	2311504	QUIXERÉ
1041	23	CE	2311603	REDENÇÃO
1042	23	CE	2311702	RERIUTABA
1043	23	CE	2311801	RUSSAS
1044	23	CE	2311900	SABOEIRO
1045	23	CE	2311959	SALITRE
1046	23	CE	2312007	SANTANA DO ACARAÚ
1047	23	CE	2312106	SANTANA DO CARIRI
1048	23	CE	2312205	SANTA QUITÉRIA
1049	23	CE	2312304	SÃO BENEDITO
1050	23	CE	2312403	SÃO GONÇALO DO AMARANTE
1051	23	CE	2312502	SÃO JOÃO DO JAGUARIBE
1052	23	CE	2312601	SÃO LUÍS DO CURU
1053	23	CE	2312700	SENADOR POMPEU
1054	23	CE	2312809	SENADOR SÁ
1055	23	CE	2312908	SOBRAL
1056	23	CE	2313005	SOLONÓPOLE
1057	23	CE	2313104	TABULEIRO DO NORTE
1058	23	CE	2313203	TAMBORIL
1059	23	CE	2313252	TARRAFAS
1060	23	CE	2313302	TAUÁ
1061	23	CE	2313351	TEJUÇUOCA
1062	23	CE	2313401	TIANGUÁ
1063	23	CE	2313500	TRAIRI
1064	23	CE	2313559	TURURU
1065	23	CE	2313609	UBAJARA
1066	23	CE	2313708	UMARI
1067	23	CE	2313757	UMIRIM
1068	23	CE	2313807	URUBURETAMA
1069	23	CE	2313906	URUOCA
1070	23	CE	2313955	VARJOTA
1071	23	CE	2314003	VÁRZEA ALEGRE
1072	23	CE	2314102	VIÇOSA DO CEARÁ
1073	24	RN	2400109	ACARI
1074	24	RN	2400208	AÇU
1075	24	RN	2400307	AFONSO BEZERRA
1076	24	RN	2400406	ÁGUA NOVA
1077	24	RN	2400505	ALEXANDRIA
1078	24	RN	2400604	ALMINO AFONSO
1079	24	RN	2400703	ALTO DO RODRIGUES
1080	24	RN	2400802	ANGICOS
1081	24	RN	2400901	ANTÔNIO MARTINS
1082	24	RN	2401008	APODI
1083	24	RN	2401107	AREIA BRANCA
1084	24	RN	2401206	ARÊS
1085	24	RN	2401305	AUGUSTO SEVERO
1086	24	RN	2401404	BAÍA FORMOSA
1087	24	RN	2401453	BARAÚNA
1088	24	RN	2401503	BARCELONA
1089	24	RN	2401602	BENTO FERNANDES
1090	24	RN	2401651	BODÓ
1091	24	RN	2401701	BOM JESUS
1092	24	RN	2401800	BREJINHO
1093	24	RN	2401859	CAIÇARA DO NORTE
1094	24	RN	2401909	CAIÇARA DO RIO DO VENTO
1095	24	RN	2402006	CAICÓ
1096	24	RN	2402105	CAMPO REDONDO
1097	24	RN	2402204	CANGUARETAMA
1098	24	RN	2402303	CARAÚBAS
1099	24	RN	2402402	CARNAÚBA DOS DANTAS
1100	24	RN	2402501	CARNAUBAIS
1101	24	RN	2402600	CEARÁ-MIRIM
1102	24	RN	2402709	CERRO CORÁ
1103	24	RN	2402808	CORONEL EZEQUIEL
1104	24	RN	2402907	CORONEL JOÃO PESSOA
1105	24	RN	2403004	CRUZETA
1106	24	RN	2403103	CURRAIS NOVOS
1107	24	RN	2403202	DOUTOR SEVERIANO
1108	24	RN	2403251	PARNAMIRIM
1109	24	RN	2403301	ENCANTO
1110	24	RN	2403400	EQUADOR
1111	24	RN	2403509	ESPÍRITO SANTO
1112	24	RN	2403608	EXTREMOZ
1113	24	RN	2403707	FELIPE GUERRA
1114	24	RN	2403756	FERNANDO PEDROZA
1115	24	RN	2403806	FLORÂNIA
1116	24	RN	2403905	FRANCISCO DANTAS
1117	24	RN	2404002	FRUTUOSO GOMES
1118	24	RN	2404101	GALINHOS
1119	24	RN	2404200	GOIANINHA
1120	24	RN	2404309	GOVERNADOR DIX-SEPT ROSADO
1121	24	RN	2404408	GROSSOS
1122	24	RN	2404507	GUAMARÉ
1123	24	RN	2404606	IELMO MARINHO
1124	24	RN	2404705	IPANGUAÇU
1125	24	RN	2404804	IPUEIRA
1126	24	RN	2404853	ITAJÁ
1127	24	RN	2404903	ITAÚ
1128	24	RN	2405009	JAÇANÃ
1129	24	RN	2405108	JANDAÍRA
1130	24	RN	2405207	JANDUÍS
1131	24	RN	2405306	JANUÁRIO CICCO
1132	24	RN	2405405	JAPI
1133	24	RN	2405504	JARDIM DE ANGICOS
1134	24	RN	2405603	JARDIM DE PIRANHAS
1135	24	RN	2405702	JARDIM DO SERIDÓ
1136	24	RN	2405801	JOÃO CÂMARA
1137	24	RN	2405900	JOÃO DIAS
1138	24	RN	2406007	JOSÉ DA PENHA
1139	24	RN	2406106	JUCURUTU
1140	24	RN	2406155	JUNDIÁ
1141	24	RN	2406205	LAGOA D ANTA
1142	24	RN	2406304	LAGOA DE PEDRAS
1143	24	RN	2406403	LAGOA DE VELHOS
1144	24	RN	2406502	LAGOA NOVA
1145	24	RN	2406601	LAGOA SALGADA
1146	24	RN	2406700	LAJES
1147	24	RN	2406809	LAJES PINTADAS
1148	24	RN	2406908	LUCRÉCIA
1149	24	RN	2407005	LUÍS GOMES
1150	24	RN	2407104	MACAÍBA
1151	24	RN	2407203	MACAU
1152	24	RN	2407252	MAJOR SALES
1153	24	RN	2407302	MARCELINO VIEIRA
1154	24	RN	2407401	MARTINS
1155	24	RN	2407500	MAXARANGUAPE
1156	24	RN	2407609	MESSIAS TARGINO
1157	24	RN	2407708	MONTANHAS
1158	24	RN	2407807	MONTE ALEGRE
1159	24	RN	2407906	MONTE DAS GAMELEIRAS
1160	24	RN	2408003	MOSSORÓ
1161	24	RN	2408102	NATAL
1162	24	RN	2408201	NÍSIA FLORESTA
1163	24	RN	2408300	NOVA CRUZ
1164	24	RN	2408409	OLHO-D ÁGUA DO BORGES
1165	24	RN	2408508	OURO BRANCO
1166	24	RN	2408607	PARANÁ
1167	24	RN	2408706	PARAÚ
1168	24	RN	2408805	PARAZINHO
1169	24	RN	2408904	PARELHAS
1170	24	RN	2408953	RIO DO FOGO
1171	24	RN	2409100	PASSA E FICA
1172	24	RN	2409209	PASSAGEM
1173	24	RN	2409308	PATU
1174	24	RN	2409332	SANTA MARIA
1175	24	RN	2409407	PAU DOS FERROS
1176	24	RN	2409506	PEDRA GRANDE
1177	24	RN	2409605	PEDRA PRETA
1178	24	RN	2409704	PEDRO AVELINO
1179	24	RN	2409803	PEDRO VELHO
1180	24	RN	2409902	PENDÊNCIAS
1181	24	RN	2410009	PILÕES
1182	24	RN	2410108	POÇO BRANCO
1183	24	RN	2410207	PORTALEGRE
1184	24	RN	2410256	PORTO DO MANGUE
1185	24	RN	2410306	PRESIDENTE JUSCELINO
1186	24	RN	2410405	PUREZA
1187	24	RN	2410504	RAFAEL FERNANDES
1188	24	RN	2410603	RAFAEL GODEIRO
1189	24	RN	2410702	RIACHO DA CRUZ
1190	24	RN	2410801	RIACHO DE SANTANA
1191	24	RN	2410900	RIACHUELO
1192	24	RN	2411007	RODOLFO FERNANDES
1193	24	RN	2411056	TIBAU
1194	24	RN	2411106	RUY BARBOSA
1195	24	RN	2411205	SANTA CRUZ
1196	24	RN	2411403	SANTANA DO MATOS
1197	24	RN	2411429	SANTANA DO SERIDÓ
1198	24	RN	2411502	SANTO ANTÔNIO
1199	24	RN	2411601	SÃO BENTO DO NORTE
1200	24	RN	2411700	SÃO BENTO DO TRAIRÍ
1201	24	RN	2411809	SÃO FERNANDO
1202	24	RN	2411908	SÃO FRANCISCO DO OESTE
1203	24	RN	2412005	SÃO GONÇALO DO AMARANTE
1204	24	RN	2412104	SÃO JOÃO DO SABUGI
1205	24	RN	2412203	SÃO JOSÉ DE MIPIBU
1206	24	RN	2412302	SÃO JOSÉ DO CAMPESTRE
1207	24	RN	2412401	SÃO JOSÉ DO SERIDÓ
1208	24	RN	2412500	SÃO MIGUEL
1209	24	RN	2412559	SÃO MIGUEL DE TOUROS
1210	24	RN	2412609	SÃO PAULO DO POTENGI
1211	24	RN	2412708	SÃO PEDRO
1212	24	RN	2412807	SÃO RAFAEL
1213	24	RN	2412906	SÃO TOMÉ
1214	24	RN	2413003	SÃO VICENTE
1215	24	RN	2413102	SENADOR ELÓI DE SOUZA
1216	24	RN	2413201	SENADOR GEORGINO AVELINO
1217	24	RN	2413300	SERRA DE SÃO BENTO
1218	24	RN	2413359	SERRA DO MEL
1219	24	RN	2413409	SERRA NEGRA DO NORTE
1220	24	RN	2413508	SERRINHA
1221	24	RN	2413557	SERRINHA DOS PINTOS
1222	24	RN	2413607	SEVERIANO MELO
1223	24	RN	2413706	SÍTIO NOVO
1224	24	RN	2413805	TABOLEIRO GRANDE
1225	24	RN	2413904	TAIPU
1226	24	RN	2414001	TANGARÁ
1227	24	RN	2414100	TENENTE ANANIAS
1228	24	RN	2414159	TENENTE LAURENTINO CRUZ
1229	24	RN	2414209	TIBAU DO SUL
1230	24	RN	2414308	TIMBAÚBA DOS BATISTAS
1231	24	RN	2414407	TOUROS
1232	24	RN	2414456	TRIUNFO POTIGUAR
1233	24	RN	2414506	UMARIZAL
1234	24	RN	2414605	UPANEMA
1235	24	RN	2414704	VÁRZEA
1236	24	RN	2414753	VENHA-VER
1237	24	RN	2414803	VERA CRUZ
1238	24	RN	2414902	VIÇOSA
1239	24	RN	2415008	VILA FLOR
1240	25	PB	2500106	ÁGUA BRANCA
1241	25	PB	2500205	AGUIAR
1242	25	PB	2500304	ALAGOA GRANDE
1243	25	PB	2500403	ALAGOA NOVA
1244	25	PB	2500502	ALAGOINHA
1245	25	PB	2500536	ALCANTIL
1246	25	PB	2500577	ALGODÃO DE JANDAÍRA
1247	25	PB	2500601	ALHANDRA
1248	25	PB	2500700	SÃO JOÃO DO RIO DO PEIXE
1249	25	PB	2500734	AMPARO
1250	25	PB	2500775	APARECIDA
1251	25	PB	2500809	ARAÇAGI
1252	25	PB	2500908	ARARA
1253	25	PB	2501005	ARARUNA
1254	25	PB	2501104	AREIA
1255	25	PB	2501153	AREIA DE BARAÚNAS
1256	25	PB	2501203	AREIAL
1257	25	PB	2501302	AROEIRAS
1258	25	PB	2501351	ASSUNÇÃO
1259	25	PB	2501401	BAÍA DA TRAIÇÃO
1260	25	PB	2501500	BANANEIRAS
1261	25	PB	2501534	BARAÚNA
1262	25	PB	2501575	BARRA DE SANTANA
1263	25	PB	2501609	BARRA DE SANTA ROSA
1264	25	PB	2501708	BARRA DE SÃO MIGUEL
1265	25	PB	2501807	BAYEUX
1266	25	PB	2501906	BELÉM
1267	25	PB	2502003	BELÉM DO BREJO DO CRUZ
1268	25	PB	2502052	BERNARDINO BATISTA
1269	25	PB	2502102	BOA VENTURA
1270	25	PB	2502151	BOA VISTA
1271	25	PB	2502201	BOM JESUS
1272	25	PB	2502300	BOM SUCESSO
1273	25	PB	2502409	BONITO DE SANTA FÉ
1274	25	PB	2502508	BOQUEIRÃO
1275	25	PB	2502607	IGARACY
1276	25	PB	2502706	BORBOREMA
1277	25	PB	2502805	BREJO DO CRUZ
1278	25	PB	2502904	BREJO DOS SANTOS
1279	25	PB	2503001	CAAPORÃ
1280	25	PB	2503100	CABACEIRAS
1281	25	PB	2503209	CABEDELO
1282	25	PB	2503308	CACHOEIRA DOS ÍNDIOS
1283	25	PB	2503407	CACIMBA DE AREIA
1284	25	PB	2503506	CACIMBA DE DENTRO
1285	25	PB	2503555	CACIMBAS
1286	25	PB	2503605	CAIÇARA
1287	25	PB	2503704	CAJAZEIRAS
1288	25	PB	2503753	CAJAZEIRINHAS
1289	25	PB	2503803	CALDAS BRANDÃO
1290	25	PB	2503902	CAMALAÚ
1291	25	PB	2504009	CAMPINA GRANDE
1292	25	PB	2504033	CAPIM
1293	25	PB	2504074	CARAÚBAS
1294	25	PB	2504108	CARRAPATEIRA
1295	25	PB	2504157	CASSERENGUE
1296	25	PB	2504207	CATINGUEIRA
1297	25	PB	2504306	CATOLÉ DO ROCHA
1298	25	PB	2504355	CATURITÉ
1299	25	PB	2504405	CONCEIÇÃO
1300	25	PB	2504504	CONDADO
1301	25	PB	2504603	CONDE
1302	25	PB	2504702	CONGO
1303	25	PB	2504801	COREMAS
1304	25	PB	2504850	COXIXOLA
1305	25	PB	2504900	CRUZ DO ESPÍRITO SANTO
1306	25	PB	2505006	CUBATI
1307	25	PB	2505105	CUITÉ
1308	25	PB	2505204	CUITEGI
1309	25	PB	2505238	CUITÉ DE MAMANGUAPE
1310	25	PB	2505279	CURRAL DE CIMA
1311	25	PB	2505303	CURRAL VELHO
1312	25	PB	2505352	DAMIÃO
1313	25	PB	2505402	DESTERRO
1314	25	PB	2505501	VISTA SERRANA
1315	25	PB	2505600	DIAMANTE
1316	25	PB	2505709	DONA INÊS
1317	25	PB	2505808	DUAS ESTRADAS
1318	25	PB	2505907	EMAS
1319	25	PB	2506004	ESPERANÇA
1320	25	PB	2506103	FAGUNDES
1321	25	PB	2506202	FREI MARTINHO
1322	25	PB	2506251	GADO BRAVO
1323	25	PB	2506301	GUARABIRA
1324	25	PB	2506400	GURINHÉM
1325	25	PB	2506509	GURJÃO
1326	25	PB	2506608	IBIARA
1327	25	PB	2506707	IMACULADA
1328	25	PB	2506806	INGÁ
1329	25	PB	2506905	ITABAIANA
1330	25	PB	2507002	ITAPORANGA
1331	25	PB	2507101	ITAPOROROCA
1332	25	PB	2507200	ITATUBA
1333	25	PB	2507309	JACARAÚ
1334	25	PB	2507408	JERICÓ
1335	25	PB	2507507	JOÃO PESSOA
1336	25	PB	2507606	JUAREZ TÁVORA
1337	25	PB	2507705	JUAZEIRINHO
1338	25	PB	2507804	JUNCO DO SERIDÓ
1339	25	PB	2507903	JURIPIRANGA
1340	25	PB	2508000	JURU
1341	25	PB	2508109	LAGOA
1342	25	PB	2508208	LAGOA DE DENTRO
1343	25	PB	2508307	LAGOA SECA
1344	25	PB	2508406	LASTRO
1345	25	PB	2508505	LIVRAMENTO
1346	25	PB	2508554	LOGRADOURO
1347	25	PB	2508604	LUCENA
1348	25	PB	2508703	MÃE D ÁGUA
1349	25	PB	2508802	MALTA
1350	25	PB	2508901	MAMANGUAPE
1351	25	PB	2509008	MANAÍRA
1352	25	PB	2509057	MARCAÇÃO
1353	25	PB	2509107	MARI
1354	25	PB	2509156	MARIZÓPOLIS
1355	25	PB	2509206	MASSARANDUBA
1356	25	PB	2509305	MATARACA
1357	25	PB	2509339	MATINHAS
1358	25	PB	2509370	MATO GROSSO
1359	25	PB	2509396	MATURÉIA
1360	25	PB	2509404	MOGEIRO
1361	25	PB	2509503	MONTADAS
1362	25	PB	2509602	MONTE HOREBE
1363	25	PB	2509701	MONTEIRO
1364	25	PB	2509800	MULUNGU
1365	25	PB	2509909	NATUBA
1366	25	PB	2510006	NAZAREZINHO
1367	25	PB	2510105	NOVA FLORESTA
1368	25	PB	2510204	NOVA OLINDA
1369	25	PB	2510303	NOVA PALMEIRA
1370	25	PB	2510402	OLHO D ÁGUA
1371	25	PB	2510501	OLIVEDOS
1372	25	PB	2510600	OURO VELHO
1373	25	PB	2510659	PARARI
1374	25	PB	2510709	PASSAGEM
1375	25	PB	2510808	PATOS
1376	25	PB	2510907	PAULISTA
1377	25	PB	2511004	PEDRA BRANCA
1378	25	PB	2511103	PEDRA LAVRADA
1379	25	PB	2511202	PEDRAS DE FOGO
1380	25	PB	2511301	PIANCÓ
1381	25	PB	2511400	PICUÍ
1382	25	PB	2511509	PILAR
1383	25	PB	2511608	PILÕES
1384	25	PB	2511707	PILÕEZINHOS
1385	25	PB	2511806	PIRPIRITUBA
1386	25	PB	2511905	PITIMBU
1387	25	PB	2512002	POCINHOS
1388	25	PB	2512036	POÇO DANTAS
1389	25	PB	2512077	POÇO DE JOSÉ DE MOURA
1390	25	PB	2512101	POMBAL
1391	25	PB	2512200	PRATA
1392	25	PB	2512309	PRINCESA ISABEL
1393	25	PB	2512408	PUXINANÃ
1394	25	PB	2512507	QUEIMADAS
1395	25	PB	2512606	QUIXABÁ
1396	25	PB	2512705	REMÍGIO
1397	25	PB	2512721	PEDRO RÉGIS
1398	25	PB	2512747	RIACHÃO
1399	25	PB	2512754	RIACHÃO DO BACAMARTE
1400	25	PB	2512762	RIACHÃO DO POÇO
1401	25	PB	2512788	RIACHO DE SANTO ANTÔNIO
1402	25	PB	2512804	RIACHO DOS CAVALOS
1403	25	PB	2512903	RIO TINTO
1404	25	PB	2513000	SALGADINHO
1405	25	PB	2513109	SALGADO DE SÃO FÉLIX
1406	25	PB	2513158	SANTA CECÍLIA
1407	25	PB	2513208	SANTA CRUZ
1408	25	PB	2513307	SANTA HELENA
1409	25	PB	2513356	SANTA INÊS
1410	25	PB	2513406	SANTA LUZIA
1411	25	PB	2513505	SANTANA DE MANGUEIRA
1412	25	PB	2513604	SANTANA DOS GARROTES
1413	25	PB	2513653	SANTARÉM
1414	25	PB	2513703	SANTA RITA
1415	25	PB	2513802	SANTA TERESINHA
1416	25	PB	2513851	SANTO ANDRÉ
1417	25	PB	2513901	SÃO BENTO
1418	25	PB	2513927	SÃO BENTINHO
1419	25	PB	2513943	SÃO DOMINGOS DO CARIRI
1420	25	PB	2513968	SÃO DOMINGOS DE POMBAL
1421	25	PB	2513984	SÃO FRANCISCO
1422	25	PB	2514008	SÃO JOÃO DO CARIRI
1423	25	PB	2514107	SÃO JOÃO DO TIGRE
1424	25	PB	2514206	SÃO JOSÉ DA LAGOA TAPADA
1425	25	PB	2514305	SÃO JOSÉ DE CAIANA
1426	25	PB	2514404	SÃO JOSÉ DE ESPINHARAS
1427	25	PB	2514453	SÃO JOSÉ DOS RAMOS
1428	25	PB	2514503	SÃO JOSÉ DE PIRANHAS
1429	25	PB	2514552	SÃO JOSÉ DE PRINCESA
1430	25	PB	2514602	SÃO JOSÉ DO BONFIM
1431	25	PB	2514651	SÃO JOSÉ DO BREJO DO CRUZ
1432	25	PB	2514701	SÃO JOSÉ DO SABUGI
1433	25	PB	2514800	SÃO JOSÉ DOS CORDEIROS
1434	25	PB	2514909	SÃO MAMEDE
1435	25	PB	2515005	SÃO MIGUEL DE TAIPU
1436	25	PB	2515104	SÃO SEBASTIÃO DE LAGOA DE ROÇA
1437	25	PB	2515203	SÃO SEBASTIÃO DO UMBUZEIRO
1438	25	PB	2515302	SAPÉ
1439	25	PB	2515401	SERIDÓ
1440	25	PB	2515500	SERRA BRANCA
1441	25	PB	2515609	SERRA DA RAIZ
1442	25	PB	2515708	SERRA GRANDE
1443	25	PB	2515807	SERRA REDONDA
1444	25	PB	2515906	SERRARIA
1445	25	PB	2515930	SERTÃOZINHO
1446	25	PB	2515971	SOBRADO
1447	25	PB	2516003	SOLÂNEA
1448	25	PB	2516102	SOLEDADE
1449	25	PB	2516151	SOSSÊGO
1450	25	PB	2516201	SOUSA
1451	25	PB	2516300	SUMÉ
1452	25	PB	2516409	CAMPO DE SANTANA
1453	25	PB	2516508	TAPEROÁ
1454	25	PB	2516607	TAVARES
1455	25	PB	2516706	TEIXEIRA
1456	25	PB	2516755	TENÓRIO
1457	25	PB	2516805	TRIUNFO
1458	25	PB	2516904	UIRAÚNA
1459	25	PB	2517001	UMBUZEIRO
1460	25	PB	2517100	VÁRZEA
1461	25	PB	2517209	VIEIRÓPOLIS
1462	25	PB	2517407	ZABELÊ
1463	26	PE	2600054	ABREU E LIMA
1464	26	PE	2600104	AFOGADOS DA INGAZEIRA
1465	26	PE	2600203	AFRÂNIO
1466	26	PE	2600302	AGRESTINA
1467	26	PE	2600401	ÁGUA PRETA
1468	26	PE	2600500	ÁGUAS BELAS
1469	26	PE	2600609	ALAGOINHA
1470	26	PE	2600708	ALIANÇA
1471	26	PE	2600807	ALTINHO
1472	26	PE	2600906	AMARAJI
1473	26	PE	2601003	ANGELIM
1474	26	PE	2601052	ARAÇOIABA
1475	26	PE	2601102	ARARIPINA
1476	26	PE	2601201	ARCOVERDE
1477	26	PE	2601300	BARRA DE GUABIRABA
1478	26	PE	2601409	BARREIROS
1479	26	PE	2601508	BELÉM DE MARIA
1480	26	PE	2601607	BELÉM DE SÃO FRANCISCO
1481	26	PE	2601706	BELO JARDIM
1482	26	PE	2601805	BETÂNIA
1483	26	PE	2601904	BEZERROS
1484	26	PE	2602001	BODOCÓ
1485	26	PE	2602100	BOM CONSELHO
1486	26	PE	2602209	BOM JARDIM
1487	26	PE	2602308	BONITO
1488	26	PE	2602407	BREJÃO
1489	26	PE	2602506	BREJINHO
1490	26	PE	2602605	BREJO DA MADRE DE DEUS
1491	26	PE	2602704	BUENOS AIRES
1492	26	PE	2602803	BUÍQUE
1493	26	PE	2602902	CABO DE SANTO AGOSTINHO
1494	26	PE	2603009	CABROBÓ
1495	26	PE	2603108	CACHOEIRINHA
1496	26	PE	2603207	CAETÉS
1497	26	PE	2603306	CALÇADO
1498	26	PE	2603405	CALUMBI
1499	26	PE	2603454	CAMARAGIBE
1500	26	PE	2603504	CAMOCIM DE SÃO FÉLIX
1501	26	PE	2603603	CAMUTANGA
1502	26	PE	2603702	CANHOTINHO
1503	26	PE	2603801	CAPOEIRAS
1504	26	PE	2603900	CARNAÍBA
1505	26	PE	2603926	CARNAUBEIRA DA PENHA
1506	26	PE	2604007	CARPINA
1507	26	PE	2604106	CARUARU
1508	26	PE	2604155	CASINHAS
1509	26	PE	2604205	CATENDE
1510	26	PE	2604304	CEDRO
1511	26	PE	2604403	CHÃ DE ALEGRIA
1512	26	PE	2604502	CHÃ GRANDE
1513	26	PE	2604601	CONDADO
1514	26	PE	2604700	CORRENTES
1515	26	PE	2604809	CORTÊS
1516	26	PE	2604908	CUMARU
1517	26	PE	2605004	CUPIRA
1518	26	PE	2605103	CUSTÓDIA
1519	26	PE	2605152	DORMENTES
1520	26	PE	2605202	ESCADA
1521	26	PE	2605301	EXU
1522	26	PE	2605400	FEIRA NOVA
1523	26	PE	2605459	FERNANDO DE NORONHA
1524	26	PE	2605509	FERREIROS
1525	26	PE	2605608	FLORES
1526	26	PE	2605707	FLORESTA
1527	26	PE	2605806	FREI MIGUELINHO
1528	26	PE	2605905	GAMELEIRA
1529	26	PE	2606002	GARANHUNS
1530	26	PE	2606101	GLÓRIA DO GOITÁ
1531	26	PE	2606200	GOIANA
1532	26	PE	2606309	GRANITO
1533	26	PE	2606408	GRAVATÁ
1534	26	PE	2606507	IATI
1535	26	PE	2606606	IBIMIRIM
1536	26	PE	2606705	IBIRAJUBA
1537	26	PE	2606804	IGARASSU
1538	26	PE	2606903	IGUARACI
1539	26	PE	2607000	INAJÁ
1540	26	PE	2607109	INGAZEIRA
1541	26	PE	2607208	IPOJUCA
1542	26	PE	2607307	IPUBI
1543	26	PE	2607406	ITACURUBA
1544	26	PE	2607505	ITAÍBA
1545	26	PE	2607604	ILHA DE ITAMARACÁ
1546	26	PE	2607653	ITAMBÉ
1547	26	PE	2607703	ITAPETIM
1548	26	PE	2607752	ITAPISSUMA
1549	26	PE	2607802	ITAQUITINGA
1550	26	PE	2607901	JABOATÃO DOS GUARARAPES
1551	26	PE	2607950	JAQUEIRA
1552	26	PE	2608008	JATAÚBA
1553	26	PE	2608057	JATOBÁ
1554	26	PE	2608107	JOÃO ALFREDO
1555	26	PE	2608206	JOAQUIM NABUCO
1556	26	PE	2608255	JUCATI
1557	26	PE	2608305	JUPI
1558	26	PE	2608404	JUREMA
1559	26	PE	2608453	LAGOA DO CARRO
1560	26	PE	2608503	LAGOA DO ITAENGA
1561	26	PE	2608602	LAGOA DO OURO
1562	26	PE	2608701	LAGOA DOS GATOS
1563	26	PE	2608750	LAGOA GRANDE
1564	26	PE	2608800	LAJEDO
1565	26	PE	2608909	LIMOEIRO
1566	26	PE	2609006	MACAPARANA
1567	26	PE	2609105	MACHADOS
1568	26	PE	2609154	MANARI
1569	26	PE	2609204	MARAIAL
1570	26	PE	2609303	MIRANDIBA
1571	26	PE	2609402	MORENO
1572	26	PE	2609501	NAZARÉ DA MATA
1573	26	PE	2609600	OLINDA
1574	26	PE	2609709	OROBÓ
1575	26	PE	2609808	OROCÓ
1576	26	PE	2609907	OURICURI
1577	26	PE	2610004	PALMARES
1578	26	PE	2610103	PALMEIRINA
1579	26	PE	2610202	PANELAS
1580	26	PE	2610301	PARANATAMA
1581	26	PE	2610400	PARNAMIRIM
1582	26	PE	2610509	PASSIRA
1583	26	PE	2610608	PAUDALHO
1584	26	PE	2610707	PAULISTA
1585	26	PE	2610806	PEDRA
1586	26	PE	2610905	PESQUEIRA
1587	26	PE	2611002	PETROLÂNDIA
1588	26	PE	2611101	PETROLINA
1589	26	PE	2611200	POÇÃO
1590	26	PE	2611309	POMBOS
1591	26	PE	2611408	PRIMAVERA
1592	26	PE	2611507	QUIPAPÁ
1593	26	PE	2611533	QUIXABA
1594	26	PE	2611606	RECIFE
1595	26	PE	2611705	RIACHO DAS ALMAS
1596	26	PE	2611804	RIBEIRÃO
1597	26	PE	2611903	RIO FORMOSO
1598	26	PE	2612000	SAIRÉ
1599	26	PE	2612109	SALGADINHO
1600	26	PE	2612208	SALGUEIRO
1601	26	PE	2612307	SALOÁ
1602	26	PE	2612406	SANHARÓ
1603	26	PE	2612455	SANTA CRUZ
1604	26	PE	2612471	SANTA CRUZ DA BAIXA VERDE
1605	26	PE	2612505	SANTA CRUZ DO CAPIBARIBE
1606	26	PE	2612554	SANTA FILOMENA
1607	26	PE	2612604	SANTA MARIA DA BOA VISTA
1608	26	PE	2612703	SANTA MARIA DO CAMBUCÁ
1609	26	PE	2612802	SANTA TEREZINHA
1610	26	PE	2612901	SÃO BENEDITO DO SUL
1611	26	PE	2613008	SÃO BENTO DO UNA
1612	26	PE	2613107	SÃO CAITANO
1613	26	PE	2613206	SÃO JOÃO
1614	26	PE	2613305	SÃO JOAQUIM DO MONTE
1615	26	PE	2613404	SÃO JOSÉ DA COROA GRANDE
1616	26	PE	2613503	SÃO JOSÉ DO BELMONTE
1617	26	PE	2613602	SÃO JOSÉ DO EGITO
1618	26	PE	2613701	SÃO LOURENÇO DA MATA
1619	26	PE	2613800	SÃO VICENTE FERRER
1620	26	PE	2613909	SERRA TALHADA
1621	26	PE	2614006	SERRITA
1622	26	PE	2614105	SERTÂNIA
1623	26	PE	2614204	SIRINHAÉM
1624	26	PE	2614303	MOREILÂNDIA
1625	26	PE	2614402	SOLIDÃO
1626	26	PE	2614501	SURUBIM
1627	26	PE	2614600	TABIRA
1628	26	PE	2614709	TACAIMBÓ
1629	26	PE	2614808	TACARATU
1630	26	PE	2614857	TAMANDARÉ
1631	26	PE	2615003	TAQUARITINGA DO NORTE
1632	26	PE	2615102	TEREZINHA
1633	26	PE	2615201	TERRA NOVA
1634	26	PE	2615300	TIMBAÚBA
1635	26	PE	2615409	TORITAMA
1636	26	PE	2615508	TRACUNHAÉM
1637	26	PE	2615607	TRINDADE
1638	26	PE	2615706	TRIUNFO
1639	26	PE	2615805	TUPANATINGA
1640	26	PE	2615904	TUPARETAMA
1641	26	PE	2616001	VENTUROSA
1642	26	PE	2616100	VERDEJANTE
1643	26	PE	2616183	VERTENTE DO LÉRIO
1644	26	PE	2616209	VERTENTES
1645	26	PE	2616308	VICÊNCIA
1646	26	PE	2616407	VITÓRIA DE SANTO ANTÃO
1647	26	PE	2616506	XEXÉU
1648	27	AL	2700102	ÁGUA BRANCA
1649	27	AL	2700201	ANADIA
1650	27	AL	2700300	ARAPIRACA
1651	27	AL	2700409	ATALAIA
1652	27	AL	2700508	BARRA DE SANTO ANTÔNIO
1653	27	AL	2700607	BARRA DE SÃO MIGUEL
1654	27	AL	2700706	BATALHA
1655	27	AL	2700805	BELÉM
1656	27	AL	2700904	BELO MONTE
1657	27	AL	2701001	BOCA DA MATA
1658	27	AL	2701100	BRANQUINHA
1659	27	AL	2701209	CACIMBINHAS
1660	27	AL	2701308	CAJUEIRO
1661	27	AL	2701357	CAMPESTRE
1662	27	AL	2701407	CAMPO ALEGRE
1663	27	AL	2701506	CAMPO GRANDE
1664	27	AL	2701605	CANAPI
1665	27	AL	2701704	CAPELA
1666	27	AL	2701803	CARNEIROS
1667	27	AL	2701902	CHÃ PRETA
1668	27	AL	2702009	COITÉ DO NÓIA
1669	27	AL	2702108	COLÔNIA LEOPOLDINA
1670	27	AL	2702207	COQUEIRO SECO
1671	27	AL	2702306	CORURIPE
1672	27	AL	2702355	CRAÍBAS
1673	27	AL	2702405	DELMIRO GOUVEIA
1674	27	AL	2702504	DOIS RIACHOS
1675	27	AL	2702553	ESTRELA DE ALAGOAS
1676	27	AL	2702603	FEIRA GRANDE
1677	27	AL	2702702	FELIZ DESERTO
1678	27	AL	2702801	FLEXEIRAS
1679	27	AL	2702900	GIRAU DO PONCIANO
1680	27	AL	2703007	IBATEGUARA
1681	27	AL	2703106	IGACI
1682	27	AL	2703205	IGREJA NOVA
1683	27	AL	2703304	INHAPI
1684	27	AL	2703403	JACARÉ DOS HOMENS
1685	27	AL	2703502	JACUÍPE
1686	27	AL	2703601	JAPARATINGA
1687	27	AL	2703700	JARAMATAIA
1688	27	AL	2703759	JEQUIÁ DA PRAIA
1689	27	AL	2703809	JOAQUIM GOMES
1690	27	AL	2703908	JUNDIÁ
1691	27	AL	2704005	JUNQUEIRO
1692	27	AL	2704104	LAGOA DA CANOA
1693	27	AL	2704203	LIMOEIRO DE ANADIA
1694	27	AL	2704302	MACEIÓ
1695	27	AL	2704401	MAJOR ISIDORO
1696	27	AL	2704500	MARAGOGI
1697	27	AL	2704609	MARAVILHA
1698	27	AL	2704708	MARECHAL DEODORO
1699	27	AL	2704807	MARIBONDO
1700	27	AL	2704906	MAR VERMELHO
1701	27	AL	2705002	MATA GRANDE
1702	27	AL	2705101	MATRIZ DE CAMARAGIBE
1703	27	AL	2705200	MESSIAS
1704	27	AL	2705309	MINADOR DO NEGRÃO
1705	27	AL	2705408	MONTEIRÓPOLIS
1706	27	AL	2705507	MURICI
1707	27	AL	2705606	NOVO LINO
1708	27	AL	2705705	OLHO D ÁGUA DAS FLORES
1709	27	AL	2705804	OLHO D ÁGUA DO CASADO
1710	27	AL	2705903	OLHO D ÁGUA GRANDE
1711	27	AL	2706000	OLIVENÇA
1712	27	AL	2706109	OURO BRANCO
1713	27	AL	2706208	PALESTINA
1714	27	AL	2706307	PALMEIRA DOS ÍNDIOS
1715	27	AL	2706406	PÃO DE AÇÚCAR
1716	27	AL	2706422	PARICONHA
1717	27	AL	2706448	PARIPUEIRA
1718	27	AL	2706505	PASSO DE CAMARAGIBE
1719	27	AL	2706604	PAULO JACINTO
1720	27	AL	2706703	PENEDO
1721	27	AL	2706802	PIAÇABUÇU
1722	27	AL	2706901	PILAR
1723	27	AL	2707008	PINDOBA
1724	27	AL	2707107	PIRANHAS
1725	27	AL	2707206	POÇO DAS TRINCHEIRAS
1726	27	AL	2707305	PORTO CALVO
1727	27	AL	2707404	PORTO DE PEDRAS
1728	27	AL	2707503	PORTO REAL DO COLÉGIO
1729	27	AL	2707602	QUEBRANGULO
1730	27	AL	2707701	RIO LARGO
1731	27	AL	2707800	ROTEIRO
1732	27	AL	2707909	SANTA LUZIA DO NORTE
1733	27	AL	2708006	SANTANA DO IPANEMA
1734	27	AL	2708105	SANTANA DO MUNDAÚ
1735	27	AL	2708204	SÃO BRÁS
1736	27	AL	2708303	SÃO JOSÉ DA LAJE
1737	27	AL	2708402	SÃO JOSÉ DA TAPERA
1738	27	AL	2708501	SÃO LUÍS DO QUITUNDE
1739	27	AL	2708600	SÃO MIGUEL DOS CAMPOS
1740	27	AL	2708709	SÃO MIGUEL DOS MILAGRES
1741	27	AL	2708808	SÃO SEBASTIÃO
1742	27	AL	2708907	SATUBA
1743	27	AL	2708956	SENADOR RUI PALMEIRA
1744	27	AL	2709004	TANQUE D ARCA
1745	27	AL	2709103	TAQUARANA
1746	27	AL	2709152	TEOTÔNIO VILELA
1747	27	AL	2709202	TRAIPU
1748	27	AL	2709301	UNIÃO DOS PALMARES
1749	27	AL	2709400	VIÇOSA
1750	28	SE	2800100	AMPARO DE SÃO FRANCISCO
1751	28	SE	2800209	AQUIDABÃ
1752	28	SE	2800308	ARACAJU
1753	28	SE	2800407	ARAUÁ
1754	28	SE	2800506	AREIA BRANCA
1755	28	SE	2800605	BARRA DOS COQUEIROS
1756	28	SE	2800670	BOQUIM
1757	28	SE	2800704	BREJO GRANDE
1758	28	SE	2801009	CAMPO DO BRITO
1759	28	SE	2801108	CANHOBA
1760	28	SE	2801207	CANINDÉ DE SÃO FRANCISCO
1761	28	SE	2801306	CAPELA
1762	28	SE	2801405	CARIRA
1763	28	SE	2801504	CARMÓPOLIS
1764	28	SE	2801603	CEDRO DE SÃO JOÃO
1765	28	SE	2801702	CRISTINÁPOLIS
1766	28	SE	2801900	CUMBE
1767	28	SE	2802007	DIVINA PASTORA
1768	28	SE	2802106	ESTÂNCIA
1769	28	SE	2802205	FEIRA NOVA
1770	28	SE	2802304	FREI PAULO
1771	28	SE	2802403	GARARU
1772	28	SE	2802502	GENERAL MAYNARD
1773	28	SE	2802601	GRACHO CARDOSO
1774	28	SE	2802700	ILHA DAS FLORES
1775	28	SE	2802809	INDIAROBA
1776	28	SE	2802908	ITABAIANA
1777	28	SE	2803005	ITABAIANINHA
1778	28	SE	2803104	ITABI
1779	28	SE	2803203	ITAPORANGA D AJUDA
1780	28	SE	2803302	JAPARATUBA
1781	28	SE	2803401	JAPOATÃ
1782	28	SE	2803500	LAGARTO
1783	28	SE	2803609	LARANJEIRAS
1784	28	SE	2803708	MACAMBIRA
1785	28	SE	2803807	MALHADA DOS BOIS
1786	28	SE	2803906	MALHADOR
1787	28	SE	2804003	MARUIM
1788	28	SE	2804102	MOITA BONITA
1789	28	SE	2804201	MONTE ALEGRE DE SERGIPE
1790	28	SE	2804300	MURIBECA
1791	28	SE	2804409	NEÓPOLIS
1792	28	SE	2804458	NOSSA SENHORA APARECIDA
1793	28	SE	2804508	NOSSA SENHORA DA GLÓRIA
1794	28	SE	2804607	NOSSA SENHORA DAS DORES
1795	28	SE	2804706	NOSSA SENHORA DE LOURDES
1796	28	SE	2804805	NOSSA SENHORA DO SOCORRO
1797	28	SE	2804904	PACATUBA
1798	28	SE	2805000	PEDRA MOLE
1799	28	SE	2805109	PEDRINHAS
1800	28	SE	2805208	PINHÃO
1801	28	SE	2805307	PIRAMBU
1802	28	SE	2805406	POÇO REDONDO
1803	28	SE	2805505	POÇO VERDE
1804	28	SE	2805604	PORTO DA FOLHA
1805	28	SE	2805703	PROPRIÁ
1806	28	SE	2805802	RIACHÃO DO DANTAS
1807	28	SE	2805901	RIACHUELO
1808	28	SE	2806008	RIBEIRÓPOLIS
1809	28	SE	2806107	ROSÁRIO DO CATETE
1810	28	SE	2806206	SALGADO
1811	28	SE	2806305	SANTA LUZIA DO ITANHY
1812	28	SE	2806404	SANTANA DO SÃO FRANCISCO
1813	28	SE	2806503	SANTA ROSA DE LIMA
1814	28	SE	2806602	SANTO AMARO DAS BROTAS
1815	28	SE	2806701	SÃO CRISTÓVÃO
1816	28	SE	2806800	SÃO DOMINGOS
1817	28	SE	2806909	SÃO FRANCISCO
1818	28	SE	2807006	SÃO MIGUEL DO ALEIXO
1819	28	SE	2807105	SIMÃO DIAS
1820	28	SE	2807204	SIRIRI
1821	28	SE	2807303	TELHA
1822	28	SE	2807402	TOBIAS BARRETO
1823	28	SE	2807501	TOMAR DO GERU
1824	28	SE	2807600	UMBAÚBA
1825	29	BA	2900108	ABAÍRA
1826	29	BA	2900207	ABARÉ
1827	29	BA	2900306	ACAJUTIBA
1828	29	BA	2900355	ADUSTINA
1829	29	BA	2900405	ÁGUA FRIA
1830	29	BA	2900504	ÉRICO CARDOSO
1831	29	BA	2900603	AIQUARA
1832	29	BA	2900702	ALAGOINHAS
1833	29	BA	2900801	ALCOBAÇA
1834	29	BA	2900900	ALMADINA
1835	29	BA	2901007	AMARGOSA
1836	29	BA	2901106	AMÉLIA RODRIGUES
1837	29	BA	2901155	AMÉRICA DOURADA
1838	29	BA	2901205	ANAGÉ
1839	29	BA	2901304	ANDARAÍ
1840	29	BA	2901353	ANDORINHA
1841	29	BA	2901403	ANGICAL
1842	29	BA	2901502	ANGUERA
1843	29	BA	2901601	ANTAS
1844	29	BA	2901700	ANTÔNIO CARDOSO
1845	29	BA	2901809	ANTÔNIO GONÇALVES
1846	29	BA	2901908	APORÁ
1847	29	BA	2901957	APUAREMA
1848	29	BA	2902005	ARACATU
1849	29	BA	2902054	ARAÇAS
1850	29	BA	2902104	ARACI
1851	29	BA	2902203	ARAMARI
1852	29	BA	2902252	ARATACA
1853	29	BA	2902302	ARATUÍPE
1854	29	BA	2902401	AURELINO LEAL
1855	29	BA	2902500	BAIANÓPOLIS
1856	29	BA	2902609	BAIXA GRANDE
1857	29	BA	2902658	BANZAÊ
1858	29	BA	2902708	BARRA
1859	29	BA	2902807	BARRA DA ESTIVA
1860	29	BA	2902906	BARRA DO CHOÇA
1861	29	BA	2903003	BARRA DO MENDES
1862	29	BA	2903102	BARRA DO ROCHA
1863	29	BA	2903201	BARREIRAS
1864	29	BA	2903235	BARRO ALTO
1865	29	BA	2903276	BARROCAS
1866	29	BA	2903300	GOVERNADOR LOMANTO JÚNIOR
1867	29	BA	2903409	BELMONTE
1868	29	BA	2903508	BELO CAMPO
1869	29	BA	2903607	BIRITINGA
1870	29	BA	2903706	BOA NOVA
1871	29	BA	2903805	BOA VISTA DO TUPIM
1872	29	BA	2903904	BOM JESUS DA LAPA
1873	29	BA	2903953	BOM JESUS DA SERRA
1874	29	BA	2904001	BONINAL
1875	29	BA	2904050	BONITO
1876	29	BA	2904100	BOQUIRA
1877	29	BA	2904209	BOTUPORÃ
1878	29	BA	2904308	BREJÕES
1879	29	BA	2904407	BREJOLÂNDIA
1880	29	BA	2904506	BROTAS DE MACAÚBAS
1881	29	BA	2904605	BRUMADO
1882	29	BA	2904704	BUERAREMA
1883	29	BA	2904753	BURITIRAMA
1884	29	BA	2904803	CAATIBA
1885	29	BA	2904852	CABACEIRAS DO PARAGUAÇU
1886	29	BA	2904902	CACHOEIRA
1887	29	BA	2905008	CACULÉ
1888	29	BA	2905107	CAÉM
1889	29	BA	2905156	CAETANOS
1890	29	BA	2905206	CAETITÉ
1891	29	BA	2905305	CAFARNAUM
1892	29	BA	2905404	CAIRU
1893	29	BA	2905503	CALDEIRÃO GRANDE
1894	29	BA	2905602	CAMACAN
1895	29	BA	2905701	CAMAÇARI
1896	29	BA	2905800	CAMAMU
1897	29	BA	2905909	CAMPO ALEGRE DE LOURDES
1898	29	BA	2906006	CAMPO FORMOSO
1899	29	BA	2906105	CANÁPOLIS
1900	29	BA	2906204	CANARANA
1901	29	BA	2906303	CANAVIEIRAS
1902	29	BA	2906402	CANDEAL
1903	29	BA	2906501	CANDEIAS
1904	29	BA	2906600	CANDIBA
1905	29	BA	2906709	CÂNDIDO SALES
1906	29	BA	2906808	CANSANÇÃO
1907	29	BA	2906824	CANUDOS
1908	29	BA	2906857	CAPELA DO ALTO ALEGRE
1909	29	BA	2906873	CAPIM GROSSO
1910	29	BA	2906899	CARAÍBAS
1911	29	BA	2906907	CARAVELAS
1912	29	BA	2907004	CARDEAL DA SILVA
1913	29	BA	2907103	CARINHANHA
1914	29	BA	2907202	CASA NOVA
1915	29	BA	2907301	CASTRO ALVES
1916	29	BA	2907400	CATOLÂNDIA
1917	29	BA	2907509	CATU
1918	29	BA	2907558	CATURAMA
1919	29	BA	2907608	CENTRAL
1920	29	BA	2907707	CHORROCHÓ
1921	29	BA	2907806	CÍCERO DANTAS
1922	29	BA	2907905	CIPÓ
1923	29	BA	2908002	COARACI
1924	29	BA	2908101	COCOS
1925	29	BA	2908200	CONCEIÇÃO DA FEIRA
1926	29	BA	2908309	CONCEIÇÃO DO ALMEIDA
1927	29	BA	2908408	CONCEIÇÃO DO COITÉ
1928	29	BA	2908507	CONCEIÇÃO DO JACUÍPE
1929	29	BA	2908606	CONDE
1930	29	BA	2908705	CONDEÚBA
1931	29	BA	2908804	CONTENDAS DO SINCORÁ
1932	29	BA	2908903	CORAÇÃO DE MARIA
1933	29	BA	2909000	CORDEIROS
1934	29	BA	2909109	CORIBE
1935	29	BA	2909208	CORONEL JOÃO SÁ
1936	29	BA	2909307	CORRENTINA
1937	29	BA	2909406	COTEGIPE
1938	29	BA	2909505	CRAVOLÂNDIA
1939	29	BA	2909604	CRISÓPOLIS
1940	29	BA	2909703	CRISTÓPOLIS
1941	29	BA	2909802	CRUZ DAS ALMAS
1942	29	BA	2909901	CURAÇÁ
1943	29	BA	2910008	DÁRIO MEIRA
1944	29	BA	2910057	DIAS D ÁVILA
1945	29	BA	2910107	DOM BASÍLIO
1946	29	BA	2910206	DOM MACEDO COSTA
1947	29	BA	2910305	ELÍSIO MEDRADO
1948	29	BA	2910404	ENCRUZILHADA
1949	29	BA	2910503	ENTRE RIOS
1950	29	BA	2910602	ESPLANADA
1951	29	BA	2910701	EUCLIDES DA CUNHA
1952	29	BA	2910727	EUNÁPOLIS
1953	29	BA	2910750	FÁTIMA
1954	29	BA	2910776	FEIRA DA MATA
1955	29	BA	2910800	FEIRA DE SANTANA
1956	29	BA	2910859	FILADÉLFIA
1957	29	BA	2910909	FIRMINO ALVES
1958	29	BA	2911006	FLORESTA AZUL
1959	29	BA	2911105	FORMOSA DO RIO PRETO
1960	29	BA	2911204	GANDU
1961	29	BA	2911253	GAVIÃO
1962	29	BA	2911303	GENTIO DO OURO
1963	29	BA	2911402	GLÓRIA
1964	29	BA	2911501	GONGOGI
1965	29	BA	2911600	GOVERNADOR MANGABEIRA
1966	29	BA	2911659	GUAJERU
1967	29	BA	2911709	GUANAMBI
1968	29	BA	2911808	GUARATINGA
1969	29	BA	2911857	HELIÓPOLIS
1970	29	BA	2911907	IAÇU
1971	29	BA	2912004	IBIASSUCÊ
1972	29	BA	2912103	IBICARAÍ
1973	29	BA	2912202	IBICOARA
1974	29	BA	2912301	IBICUÍ
1975	29	BA	2912400	IBIPEBA
1976	29	BA	2912509	IBIPITANGA
1977	29	BA	2912608	IBIQUERA
1978	29	BA	2912707	IBIRAPITANGA
1979	29	BA	2912806	IBIRAPUÃ
1980	29	BA	2912905	IBIRATAIA
1981	29	BA	2913002	IBITIARA
1982	29	BA	2913101	IBITITÁ
1983	29	BA	2913200	IBOTIRAMA
1984	29	BA	2913309	ICHU
1985	29	BA	2913408	IGAPORÃ
1986	29	BA	2913457	IGRAPIÚNA
1987	29	BA	2913507	IGUAÍ
1988	29	BA	2913606	ILHÉUS
1989	29	BA	2913705	INHAMBUPE
1990	29	BA	2913804	IPECAETÁ
1991	29	BA	2913903	IPIAÚ
1992	29	BA	2914000	IPIRÁ
1993	29	BA	2914109	IPUPIARA
1994	29	BA	2914208	IRAJUBA
1995	29	BA	2914307	IRAMAIA
1996	29	BA	2914406	IRAQUARA
1997	29	BA	2914505	IRARÁ
1998	29	BA	2914604	IRECÊ
1999	29	BA	2914653	ITABELA
2000	29	BA	2914703	ITABERABA
2001	29	BA	2914802	ITABUNA
2002	29	BA	2914901	ITACARÉ
2003	29	BA	2915007	ITAETÉ
2004	29	BA	2915106	ITAGI
2005	29	BA	2915205	ITAGIBÁ
2006	29	BA	2915304	ITAGIMIRIM
2007	29	BA	2915353	ITAGUAÇU DA BAHIA
2008	29	BA	2915403	ITAJU DO COLÔNIA
2009	29	BA	2915502	ITAJUÍPE
2010	29	BA	2915601	ITAMARAJU
2011	29	BA	2915700	ITAMARI
2012	29	BA	2915809	ITAMBÉ
2013	29	BA	2915908	ITANAGRA
2014	29	BA	2916005	ITANHÉM
2015	29	BA	2916104	ITAPARICA
2016	29	BA	2916203	ITAPÉ
2017	29	BA	2916302	ITAPEBI
2018	29	BA	2916401	ITAPETINGA
2019	29	BA	2916500	ITAPICURU
2020	29	BA	2916609	ITAPITANGA
2021	29	BA	2916708	ITAQUARA
2022	29	BA	2916807	ITARANTIM
2023	29	BA	2916856	ITATIM
2024	29	BA	2916906	ITIRUÇU
2025	29	BA	2917003	ITIÚBA
2026	29	BA	2917102	ITORORÓ
2027	29	BA	2917201	ITUAÇU
2028	29	BA	2917300	ITUBERÁ
2029	29	BA	2917334	IUIÚ
2030	29	BA	2917359	JABORANDI
2031	29	BA	2917409	JACARACI
2032	29	BA	2917508	JACOBINA
2033	29	BA	2917607	JAGUAQUARA
2034	29	BA	2917706	JAGUARARI
2035	29	BA	2917805	JAGUARIPE
2036	29	BA	2917904	JANDAÍRA
2037	29	BA	2918001	JEQUIÉ
2038	29	BA	2918100	JEREMOABO
2039	29	BA	2918209	JIQUIRIÇÁ
2040	29	BA	2918308	JITAÚNA
2041	29	BA	2918357	JOÃO DOURADO
2042	29	BA	2918407	JUAZEIRO
2043	29	BA	2918456	JUCURUÇU
2044	29	BA	2918506	JUSSARA
2045	29	BA	2918555	JUSSARI
2046	29	BA	2918605	JUSSIAPE
2047	29	BA	2918704	LAFAIETE COUTINHO
2048	29	BA	2918753	LAGOA REAL
2049	29	BA	2918803	LAJE
2050	29	BA	2918902	LAJEDÃO
2051	29	BA	2919009	LAJEDINHO
2052	29	BA	2919058	LAJEDO DO TABOCAL
2053	29	BA	2919108	LAMARÃO
2054	29	BA	2919157	LAPÃO
2055	29	BA	2919207	LAURO DE FREITAS
2056	29	BA	2919306	LENÇÓIS
2057	29	BA	2919405	LICÍNIO DE ALMEIDA
2058	29	BA	2919504	LIVRAMENTO DE NOSSA SENHORA
2059	29	BA	2919553	LUÍS EDUARDO MAGALHÃES
2060	29	BA	2919603	MACAJUBA
2061	29	BA	2919702	MACARANI
2062	29	BA	2919801	MACAÚBAS
2063	29	BA	2919900	MACURURÉ
2064	29	BA	2919926	MADRE DE DEUS
2065	29	BA	2919959	MAETINGA
2066	29	BA	2920007	MAIQUINIQUE
2067	29	BA	2920106	MAIRI
2068	29	BA	2920205	MALHADA
2069	29	BA	2920304	MALHADA DE PEDRAS
2070	29	BA	2920403	MANOEL VITORINO
2071	29	BA	2920452	MANSIDÃO
2072	29	BA	2920502	MARACÁS
2073	29	BA	2920601	MARAGOGIPE
2074	29	BA	2920700	MARAÚ
2075	29	BA	2920809	MARCIONÍLIO SOUZA
2076	29	BA	2920908	MASCOTE
2077	29	BA	2921005	MATA DE SÃO JOÃO
2078	29	BA	2921054	MATINA
2079	29	BA	2921104	MEDEIROS NETO
2080	29	BA	2921203	MIGUEL CALMON
2081	29	BA	2921302	MILAGRES
2082	29	BA	2921401	MIRANGABA
2083	29	BA	2921450	MIRANTE
2084	29	BA	2921500	MONTE SANTO
2085	29	BA	2921609	MORPARÁ
2086	29	BA	2921708	MORRO DO CHAPÉU
2087	29	BA	2921807	MORTUGABA
2088	29	BA	2921906	MUCUGÊ
2089	29	BA	2922003	MUCURI
2090	29	BA	2922052	MULUNGU DO MORRO
2091	29	BA	2922102	MUNDO NOVO
2092	29	BA	2922201	MUNIZ FERREIRA
2093	29	BA	2922250	MUQUÉM DE SÃO FRANCISCO
2094	29	BA	2922300	MURITIBA
2095	29	BA	2922409	MUTUÍPE
2096	29	BA	2922508	NAZARÉ
2097	29	BA	2922607	NILO PEÇANHA
2098	29	BA	2922656	NORDESTINA
2099	29	BA	2922706	NOVA CANAÃ
2100	29	BA	2922730	NOVA FÁTIMA
2101	29	BA	2922755	NOVA IBIÁ
2102	29	BA	2922805	NOVA ITARANA
2103	29	BA	2922854	NOVA REDENÇÃO
2104	29	BA	2922904	NOVA SOURE
2105	29	BA	2923001	NOVA VIÇOSA
2106	29	BA	2923035	NOVO HORIZONTE
2107	29	BA	2923050	NOVO TRIUNFO
2108	29	BA	2923100	OLINDINA
2109	29	BA	2923209	OLIVEIRA DOS BREJINHOS
2110	29	BA	2923308	OURIÇANGAS
2111	29	BA	2923357	OUROLÂNDIA
2112	29	BA	2923407	PALMAS DE MONTE ALTO
2113	29	BA	2923506	PALMEIRAS
2114	29	BA	2923605	PARAMIRIM
2115	29	BA	2923704	PARATINGA
2116	29	BA	2923803	PARIPIRANGA
2117	29	BA	2923902	PAU BRASIL
2118	29	BA	2924009	PAULO AFONSO
2119	29	BA	2924058	PÉ DE SERRA
2120	29	BA	2924108	PEDRÃO
2121	29	BA	2924207	PEDRO ALEXANDRE
2122	29	BA	2924306	PIATÃ
2123	29	BA	2924405	PILÃO ARCADO
2124	29	BA	2924504	PINDAÍ
2125	29	BA	2924603	PINDOBAÇU
2126	29	BA	2924652	PINTADAS
2127	29	BA	2924678	PIRAÍ DO NORTE
2128	29	BA	2924702	PIRIPÁ
2129	29	BA	2924801	PIRITIBA
2130	29	BA	2924900	PLANALTINO
2131	29	BA	2925006	PLANALTO
2132	29	BA	2925105	POÇÕES
2133	29	BA	2925204	POJUCA
2134	29	BA	2925253	PONTO NOVO
2135	29	BA	2925303	PORTO SEGURO
2136	29	BA	2925402	POTIRAGUÁ
2137	29	BA	2925501	PRADO
2138	29	BA	2925600	PRESIDENTE DUTRA
2139	29	BA	2925709	PRESIDENTE JÂNIO QUADROS
2140	29	BA	2925758	PRESIDENTE TANCREDO NEVES
2141	29	BA	2925808	QUEIMADAS
2142	29	BA	2925907	QUIJINGUE
2143	29	BA	2925931	QUIXABEIRA
2144	29	BA	2925956	RAFAEL JAMBEIRO
2145	29	BA	2926004	REMANSO
2146	29	BA	2926103	RETIROLÂNDIA
2147	29	BA	2926202	RIACHÃO DAS NEVES
2148	29	BA	2926301	RIACHÃO DO JACUÍPE
2149	29	BA	2926400	RIACHO DE SANTANA
2150	29	BA	2926509	RIBEIRA DO AMPARO
2151	29	BA	2926608	RIBEIRA DO POMBAL
2152	29	BA	2926657	RIBEIRÃO DO LARGO
2153	29	BA	2926707	RIO DE CONTAS
2154	29	BA	2926806	RIO DO ANTÔNIO
2155	29	BA	2926905	RIO DO PIRES
2156	29	BA	2927002	RIO REAL
2157	29	BA	2927101	RODELAS
2158	29	BA	2927200	RUY BARBOSA
2159	29	BA	2927309	SALINAS DA MARGARIDA
2160	29	BA	2927408	SALVADOR
2161	29	BA	2927507	SANTA BÁRBARA
2162	29	BA	2927606	SANTA BRÍGIDA
2163	29	BA	2927705	SANTA CRUZ CABRÁLIA
2164	29	BA	2927804	SANTA CRUZ DA VITÓRIA
2165	29	BA	2927903	SANTA INÊS
2166	29	BA	2928000	SANTALUZ
2167	29	BA	2928059	SANTA LUZIA
2168	29	BA	2928109	SANTA MARIA DA VITÓRIA
2169	29	BA	2928208	SANTANA
2170	29	BA	2928307	SANTANÓPOLIS
2171	29	BA	2928406	SANTA RITA DE CÁSSIA
2172	29	BA	2928505	SANTA TERESINHA
2173	29	BA	2928604	SANTO AMARO
2174	29	BA	2928703	SANTO ANTÔNIO DE JESUS
2175	29	BA	2928802	SANTO ESTÊVÃO
2176	29	BA	2928901	SÃO DESIDÉRIO
2177	29	BA	2928950	SÃO DOMINGOS
2178	29	BA	2929008	SÃO FÉLIX
2179	29	BA	2929057	SÃO FÉLIX DO CORIBE
2180	29	BA	2929107	SÃO FELIPE
2181	29	BA	2929206	SÃO FRANCISCO DO CONDE
2182	29	BA	2929255	SÃO GABRIEL
2183	29	BA	2929305	SÃO GONÇALO DOS CAMPOS
2184	29	BA	2929354	SÃO JOSÉ DA VITÓRIA
2185	29	BA	2929370	SÃO JOSÉ DO JACUÍPE
2186	29	BA	2929404	SÃO MIGUEL DAS MATAS
2187	29	BA	2929503	SÃO SEBASTIÃO DO PASSÉ
2188	29	BA	2929602	SAPEAÇU
2189	29	BA	2929701	SÁTIRO DIAS
2190	29	BA	2929750	SAUBARA
2191	29	BA	2929800	SAÚDE
2192	29	BA	2929909	SEABRA
2193	29	BA	2930006	SEBASTIÃO LARANJEIRAS
2194	29	BA	2930105	SENHOR DO BONFIM
2195	29	BA	2930154	SERRA DO RAMALHO
2196	29	BA	2930204	SENTO SÉ
2197	29	BA	2930303	SERRA DOURADA
2198	29	BA	2930402	SERRA PRETA
2199	29	BA	2930501	SERRINHA
2200	29	BA	2930600	SERROLÂNDIA
2201	29	BA	2930709	SIMÕES FILHO
2202	29	BA	2930758	SÍTIO DO MATO
2203	29	BA	2930766	SÍTIO DO QUINTO
2204	29	BA	2930774	SOBRADINHO
2205	29	BA	2930808	SOUTO SOARES
2206	29	BA	2930907	TABOCAS DO BREJO VELHO
2207	29	BA	2931004	TANHAÇU
2208	29	BA	2931053	TANQUE NOVO
2209	29	BA	2931103	TANQUINHO
2210	29	BA	2931202	TAPEROÁ
2211	29	BA	2931301	TAPIRAMUTÁ
2212	29	BA	2931350	TEIXEIRA DE FREITAS
2213	29	BA	2931400	TEODORO SAMPAIO
2214	29	BA	2931509	TEOFILÂNDIA
2215	29	BA	2931608	TEOLÂNDIA
2216	29	BA	2931707	TERRA NOVA
2217	29	BA	2931806	TREMEDAL
2218	29	BA	2931905	TUCANO
2219	29	BA	2932002	UAUÁ
2220	29	BA	2932101	UBAÍRA
2221	29	BA	2932200	UBAITABA
2222	29	BA	2932309	UBATÃ
2223	29	BA	2932408	UIBAÍ
2224	29	BA	2932457	UMBURANAS
2225	29	BA	2932507	UNA
2226	29	BA	2932606	URANDI
2227	29	BA	2932705	URUÇUCA
2228	29	BA	2932804	UTINGA
2229	29	BA	2932903	VALENÇA
2230	29	BA	2933000	VALENTE
2231	29	BA	2933059	VÁRZEA DA ROÇA
2232	29	BA	2933109	VÁRZEA DO POÇO
2233	29	BA	2933158	VÁRZEA NOVA
2234	29	BA	2933174	VARZEDO
2235	29	BA	2933208	VERA CRUZ
2236	29	BA	2933257	VEREDA
2237	29	BA	2933307	VITÓRIA DA CONQUISTA
2238	29	BA	2933406	WAGNER
2239	29	BA	2933455	WANDERLEY
2240	29	BA	2933505	WENCESLAU GUIMARÃES
2241	29	BA	2933604	XIQUE-XIQUE
2242	31	MG	3100104	ABADIA DOS DOURADOS
2243	31	MG	3100203	ABAETÉ
2244	31	MG	3100302	ABRE CAMPO
2245	31	MG	3100401	ACAIACA
2246	31	MG	3100500	AÇUCENA
2247	31	MG	3100609	ÁGUA BOA
2248	31	MG	3100708	ÁGUA COMPRIDA
2249	31	MG	3100807	AGUANIL
2250	31	MG	3100906	ÁGUAS FORMOSAS
2251	31	MG	3101003	ÁGUAS VERMELHAS
2252	31	MG	3101102	AIMORÉS
2253	31	MG	3101201	AIURUOCA
2254	31	MG	3101300	ALAGOA
2255	31	MG	3101409	ALBERTINA
2256	31	MG	3101508	ALÉM PARAÍBA
2257	31	MG	3101607	ALFENAS
2258	31	MG	3101631	ALFREDO VASCONCELOS
2259	31	MG	3101706	ALMENARA
2260	31	MG	3101805	ALPERCATA
2261	31	MG	3101904	ALPINÓPOLIS
2262	31	MG	3102001	ALTEROSA
2263	31	MG	3102050	ALTO CAPARAÓ
2264	31	MG	3102100	ALTO RIO DOCE
2265	31	MG	3102209	ALVARENGA
2266	31	MG	3102308	ALVINÓPOLIS
2267	31	MG	3102407	ALVORADA DE MINAS
2268	31	MG	3102506	AMPARO DO SERRA
2269	31	MG	3102605	ANDRADAS
2270	31	MG	3102704	CACHOEIRA DE PAJEÚ
2271	31	MG	3102803	ANDRELÂNDIA
2272	31	MG	3102852	ANGELÂNDIA
2273	31	MG	3102902	ANTÔNIO CARLOS
2274	31	MG	3103009	ANTÔNIO DIAS
2275	31	MG	3103108	ANTÔNIO PRADO DE MINAS
2276	31	MG	3103207	ARAÇAÍ
2277	31	MG	3103306	ARACITABA
2278	31	MG	3103405	ARAÇUAÍ
2279	31	MG	3103504	ARAGUARI
2280	31	MG	3103603	ARANTINA
2281	31	MG	3103702	ARAPONGA
2282	31	MG	3103751	ARAPORÃ
2283	31	MG	3103801	ARAPUÁ
2284	31	MG	3103900	ARAÚJOS
2285	31	MG	3104007	ARAXÁ
2286	31	MG	3104106	ARCEBURGO
2287	31	MG	3104205	ARCOS
2288	31	MG	3104304	AREADO
2289	31	MG	3104403	ARGIRITA
2290	31	MG	3104452	ARICANDUVA
2291	31	MG	3104502	ARINOS
2292	31	MG	3104601	ASTOLFO DUTRA
2293	31	MG	3104700	ATALÉIA
2294	31	MG	3104809	AUGUSTO DE LIMA
2295	31	MG	3104908	BAEPENDI
2296	31	MG	3105004	BALDIM
2297	31	MG	3105103	BAMBUÍ
2298	31	MG	3105202	BANDEIRA
2299	31	MG	3105301	BANDEIRA DO SUL
2300	31	MG	3105400	BARÃO DE COCAIS
2301	31	MG	3105509	BARÃO DE MONTE ALTO
2302	31	MG	3105608	BARBACENA
2303	31	MG	3105707	BARRA LONGA
2304	31	MG	3105905	BARROSO
2305	31	MG	3106002	BELA VISTA DE MINAS
2306	31	MG	3106101	BELMIRO BRAGA
2307	31	MG	3106200	BELO HORIZONTE
2308	31	MG	3106309	BELO ORIENTE
2309	31	MG	3106408	BELO VALE
2310	31	MG	3106507	BERILO
2311	31	MG	3106606	BERTÓPOLIS
2312	31	MG	3106655	BERIZAL
2313	31	MG	3106705	BETIM
2314	31	MG	3106804	BIAS FORTES
2315	31	MG	3106903	BICAS
2316	31	MG	3107000	BIQUINHAS
2317	31	MG	3107109	BOA ESPERANÇA
2318	31	MG	3107208	BOCAINA DE MINAS
2319	31	MG	3107307	BOCAIÚVA
2320	31	MG	3107406	BOM DESPACHO
2321	31	MG	3107505	BOM JARDIM DE MINAS
2322	31	MG	3107604	BOM JESUS DA PENHA
2323	31	MG	3107703	BOM JESUS DO AMPARO
2324	31	MG	3107802	BOM JESUS DO GALHO
2325	31	MG	3107901	BOM REPOUSO
2326	31	MG	3108008	BOM SUCESSO
2327	31	MG	3108107	BONFIM
2328	31	MG	3108206	BONFINÓPOLIS DE MINAS
2329	31	MG	3108255	BONITO DE MINAS
2330	31	MG	3108305	BORDA DA MATA
2331	31	MG	3108404	BOTELHOS
2332	31	MG	3108503	BOTUMIRIM
2333	31	MG	3108552	BRASILÂNDIA DE MINAS
2334	31	MG	3108602	BRASÍLIA DE MINAS
2335	31	MG	3108701	BRÁS PIRES
2336	31	MG	3108800	BRAÚNAS
2337	31	MG	3108909	BRASÓPOLIS
2338	31	MG	3109006	BRUMADINHO
2339	31	MG	3109105	BUENO BRANDÃO
2340	31	MG	3109204	BUENÓPOLIS
2341	31	MG	3109253	BUGRE
2342	31	MG	3109303	BURITIS
2343	31	MG	3109402	BURITIZEIRO
2344	31	MG	3109451	CABECEIRA GRANDE
2345	31	MG	3109501	CABO VERDE
2346	31	MG	3109600	CACHOEIRA DA PRATA
2347	31	MG	3109709	CACHOEIRA DE MINAS
2348	31	MG	3109808	CACHOEIRA DOURADA
2349	31	MG	3109907	CAETANÓPOLIS
2350	31	MG	3110004	CAETÉ
2351	31	MG	3110103	CAIANA
2352	31	MG	3110202	CAJURI
2353	31	MG	3110301	CALDAS
2354	31	MG	3110400	CAMACHO
2355	31	MG	3110509	CAMANDUCAIA
2356	31	MG	3110608	CAMBUÍ
2357	31	MG	3110707	CAMBUQUIRA
2358	31	MG	3110806	CAMPANÁRIO
2359	31	MG	3110905	CAMPANHA
2360	31	MG	3111002	CAMPESTRE
2361	31	MG	3111101	CAMPINA VERDE
2362	31	MG	3111150	CAMPO AZUL
2363	31	MG	3111200	CAMPO BELO
2364	31	MG	3111309	CAMPO DO MEIO
2365	31	MG	3111408	CAMPO FLORIDO
2366	31	MG	3111507	CAMPOS ALTOS
2367	31	MG	3111606	CAMPOS GERAIS
2368	31	MG	3111705	CANAÃ
2369	31	MG	3111804	CANÁPOLIS
2370	31	MG	3111903	CANA VERDE
2371	31	MG	3112000	CANDEIAS
2372	31	MG	3112059	CANTAGALO
2373	31	MG	3112109	CAPARAÓ
2374	31	MG	3112208	CAPELA NOVA
2375	31	MG	3112307	CAPELINHA
2376	31	MG	3112406	CAPETINGA
2377	31	MG	3112505	CAPIM BRANCO
2378	31	MG	3112604	CAPINÓPOLIS
2379	31	MG	3112653	CAPITÃO ANDRADE
2380	31	MG	3112703	CAPITÃO ENÉAS
2381	31	MG	3112802	CAPITÓLIO
2382	31	MG	3112901	CAPUTIRA
2383	31	MG	3113008	CARAÍ
2384	31	MG	3113107	CARANAÍBA
2385	31	MG	3113206	CARANDAÍ
2386	31	MG	3113305	CARANGOLA
2387	31	MG	3113404	CARATINGA
2388	31	MG	3113503	CARBONITA
2389	31	MG	3113602	CAREAÇU
2390	31	MG	3113701	CARLOS CHAGAS
2391	31	MG	3113800	CARMÉSIA
2392	31	MG	3113909	CARMO DA CACHOEIRA
2393	31	MG	3114006	CARMO DA MATA
2394	31	MG	3114105	CARMO DE MINAS
2395	31	MG	3114204	CARMO DO CAJURU
2396	31	MG	3114303	CARMO DO PARANAÍBA
2397	31	MG	3114402	CARMO DO RIO CLARO
2398	31	MG	3114501	CARMÓPOLIS DE MINAS
2399	31	MG	3114550	CARNEIRINHO
2400	31	MG	3114600	CARRANCAS
2401	31	MG	3114709	CARVALHÓPOLIS
2402	31	MG	3114808	CARVALHOS
2403	31	MG	3114907	CASA GRANDE
2404	31	MG	3115003	CASCALHO RICO
2405	31	MG	3115102	CÁSSIA
2406	31	MG	3115201	CONCEIÇÃO DA BARRA DE MINAS
2407	31	MG	3115300	CATAGUASES
2408	31	MG	3115359	CATAS ALTAS
2409	31	MG	3115409	CATAS ALTAS DA NORUEGA
2410	31	MG	3115458	CATUJI
2411	31	MG	3115474	CATUTI
2412	31	MG	3115508	CAXAMBU
2413	31	MG	3115607	CEDRO DO ABAETÉ
2414	31	MG	3115706	CENTRAL DE MINAS
2415	31	MG	3115805	CENTRALINA
2416	31	MG	3115904	CHÁCARA
2417	31	MG	3116001	CHALÉ
2418	31	MG	3116100	CHAPADA DO NORTE
2419	31	MG	3116159	CHAPADA GAÚCHA
2420	31	MG	3116209	CHIADOR
2421	31	MG	3116308	CIPOTÂNEA
2422	31	MG	3116407	CLARAVAL
2423	31	MG	3116506	CLARO DOS POÇÕES
2424	31	MG	3116605	CLÁUDIO
2425	31	MG	3116704	COIMBRA
2426	31	MG	3116803	COLUNA
2427	31	MG	3116902	COMENDADOR GOMES
2428	31	MG	3117009	COMERCINHO
2429	31	MG	3117108	CONCEIÇÃO DA APARECIDA
2430	31	MG	3117207	CONCEIÇÃO DAS PEDRAS
2431	31	MG	3117306	CONCEIÇÃO DAS ALAGOAS
2432	31	MG	3117405	CONCEIÇÃO DE IPANEMA
2433	31	MG	3117504	CONCEIÇÃO DO MATO DENTRO
2434	31	MG	3117603	CONCEIÇÃO DO PARÁ
2435	31	MG	3117702	CONCEIÇÃO DO RIO VERDE
2436	31	MG	3117801	CONCEIÇÃO DOS OUROS
2437	31	MG	3117836	CÔNEGO MARINHO
2438	31	MG	3117876	CONFINS
2439	31	MG	3117900	CONGONHAL
2440	31	MG	3118007	CONGONHAS
2441	31	MG	3118106	CONGONHAS DO NORTE
2442	31	MG	3118205	CONQUISTA
2443	31	MG	3118304	CONSELHEIRO LAFAIETE
2444	31	MG	3118403	CONSELHEIRO PENA
2445	31	MG	3118502	CONSOLAÇÃO
2446	31	MG	3118601	CONTAGEM
2447	31	MG	3118700	COQUEIRAL
2448	31	MG	3118809	CORAÇÃO DE JESUS
2449	31	MG	3118908	CORDISBURGO
2450	31	MG	3119005	CORDISLÂNDIA
2451	31	MG	3119104	CORINTO
2452	31	MG	3119203	COROACI
2453	31	MG	3119302	COROMANDEL
2454	31	MG	3119401	CORONEL FABRICIANO
2455	31	MG	3119500	CORONEL MURTA
2456	31	MG	3119609	CORONEL PACHECO
2457	31	MG	3119708	CORONEL XAVIER CHAVES
2458	31	MG	3119807	CÓRREGO DANTA
2459	31	MG	3119906	CÓRREGO DO BOM JESUS
2460	31	MG	3119955	CÓRREGO FUNDO
2461	31	MG	3120003	CÓRREGO NOVO
2462	31	MG	3120102	COUTO DE MAGALHÃES DE MINAS
2463	31	MG	3120151	CRISÓLITA
2464	31	MG	3120201	CRISTAIS
2465	31	MG	3120300	CRISTÁLIA
2466	31	MG	3120409	CRISTIANO OTONI
2467	31	MG	3120508	CRISTINA
2468	31	MG	3120607	CRUCILÂNDIA
2469	31	MG	3120706	CRUZEIRO DA FORTALEZA
2470	31	MG	3120805	CRUZÍLIA
2471	31	MG	3120839	CUPARAQUE
2472	31	MG	3120870	CURRAL DE DENTRO
2473	31	MG	3120904	CURVELO
2474	31	MG	3121001	DATAS
2475	31	MG	3121100	DELFIM MOREIRA
2476	31	MG	3121209	DELFINÓPOLIS
2477	31	MG	3121258	DELTA
2478	31	MG	3121308	DESCOBERTO
2479	31	MG	3121407	DESTERRO DE ENTRE RIOS
2480	31	MG	3121506	DESTERRO DO MELO
2481	31	MG	3121605	DIAMANTINA
2482	31	MG	3121704	DIOGO DE VASCONCELOS
2483	31	MG	3121803	DIONÍSIO
2484	31	MG	3121902	DIVINÉSIA
2485	31	MG	3122009	DIVINO
2486	31	MG	3122108	DIVINO DAS LARANJEIRAS
2487	31	MG	3122207	DIVINOLÂNDIA DE MINAS
2488	31	MG	3122306	DIVINÓPOLIS
2489	31	MG	3122355	DIVISA ALEGRE
2490	31	MG	3122405	DIVISA NOVA
2491	31	MG	3122454	DIVISÓPOLIS
2492	31	MG	3122470	DOM BOSCO
2493	31	MG	3122504	DOM CAVATI
2494	31	MG	3122603	DOM JOAQUIM
2495	31	MG	3122702	DOM SILVÉRIO
2496	31	MG	3122801	DOM VIÇOSO
2497	31	MG	3122900	DONA EUSÉBIA
2498	31	MG	3123007	DORES DE CAMPOS
2499	31	MG	3123106	DORES DE GUANHÃES
2500	31	MG	3123205	DORES DO INDAIÁ
2501	31	MG	3123304	DORES DO TURVO
2502	31	MG	3123403	DORESÓPOLIS
2503	31	MG	3123502	DOURADOQUARA
2504	31	MG	3123528	DURANDÉ
2505	31	MG	3123601	ELÓI MENDES
2506	31	MG	3123700	ENGENHEIRO CALDAS
2507	31	MG	3123809	ENGENHEIRO NAVARRO
2508	31	MG	3123858	ENTRE FOLHAS
2509	31	MG	3123908	ENTRE RIOS DE MINAS
2510	31	MG	3124005	ERVÁLIA
2511	31	MG	3124104	ESMERALDAS
2512	31	MG	3124203	ESPERA FELIZ
2513	31	MG	3124302	ESPINOSA
2514	31	MG	3124401	ESPÍRITO SANTO DO DOURADO
2515	31	MG	3124500	ESTIVA
2516	31	MG	3124609	ESTRELA DALVA
2517	31	MG	3124708	ESTRELA DO INDAIÁ
2518	31	MG	3124807	ESTRELA DO SUL
2519	31	MG	3124906	EUGENÓPOLIS
2520	31	MG	3125002	EWBANK DA CÂMARA
2521	31	MG	3125101	EXTREMA
2522	31	MG	3125200	FAMA
2523	31	MG	3125309	FARIA LEMOS
2524	31	MG	3125408	FELÍCIO DOS SANTOS
2525	31	MG	3125507	SÃO GONÇALO DO RIO PRETO
2526	31	MG	3125606	FELISBURGO
2527	31	MG	3125705	FELIXLÂNDIA
2528	31	MG	3125804	FERNANDES TOURINHO
2529	31	MG	3125903	FERROS
2530	31	MG	3125952	FERVEDOURO
2531	31	MG	3126000	FLORESTAL
2532	31	MG	3126109	FORMIGA
2533	31	MG	3126208	FORMOSO
2534	31	MG	3126307	FORTALEZA DE MINAS
2535	31	MG	3126406	FORTUNA DE MINAS
2536	31	MG	3126505	FRANCISCO BADARÓ
2537	31	MG	3126604	FRANCISCO DUMONT
2538	31	MG	3126703	FRANCISCO SÁ
2539	31	MG	3126752	FRANCISCÓPOLIS
2540	31	MG	3126802	FREI GASPAR
2541	31	MG	3126901	FREI INOCÊNCIO
2542	31	MG	3126950	FREI LAGONEGRO
2543	31	MG	3127008	FRONTEIRA
2544	31	MG	3127057	FRONTEIRA DOS VALES
2545	31	MG	3127073	FRUTA DE LEITE
2546	31	MG	3127107	FRUTAL
2547	31	MG	3127206	FUNILÂNDIA
2548	31	MG	3127305	GALILÉIA
2549	31	MG	3127339	GAMELEIRAS
2550	31	MG	3127354	GLAUCILÂNDIA
2551	31	MG	3127370	GOIABEIRA
2552	31	MG	3127388	GOIANÁ
2553	31	MG	3127404	GONÇALVES
2554	31	MG	3127503	GONZAGA
2555	31	MG	3127602	GOUVEIA
2556	31	MG	3127701	GOVERNADOR VALADARES
2557	31	MG	3127800	GRÃO MOGOL
2558	31	MG	3127909	GRUPIARA
2559	31	MG	3128006	GUANHÃES
2560	31	MG	3128105	GUAPÉ
2561	31	MG	3128204	GUARACIABA
2562	31	MG	3128253	GUARACIAMA
2563	31	MG	3128303	GUARANÉSIA
2564	31	MG	3128402	GUARANI
2565	31	MG	3128501	GUARARÁ
2566	31	MG	3128600	GUARDA-MOR
2567	31	MG	3128709	GUAXUPÉ
2568	31	MG	3128808	GUIDOVAL
2569	31	MG	3128907	GUIMARÂNIA
2570	31	MG	3129004	GUIRICEMA
2571	31	MG	3129103	GURINHATÃ
2572	31	MG	3129202	HELIODORA
2573	31	MG	3129301	IAPU
2574	31	MG	3129400	IBERTIOGA
2575	31	MG	3129509	IBIÁ
2576	31	MG	3129608	IBIAÍ
2577	31	MG	3129657	IBIRACATU
2578	31	MG	3129707	IBIRACI
2579	31	MG	3129806	IBIRITÉ
2580	31	MG	3129905	IBITIÚRA DE MINAS
2581	31	MG	3130002	IBITURUNA
2582	31	MG	3130051	ICARAÍ DE MINAS
2583	31	MG	3130101	IGARAPÉ
2584	31	MG	3130200	IGARATINGA
2585	31	MG	3130309	IGUATAMA
2586	31	MG	3130408	IJACI
2587	31	MG	3130507	ILICÍNEA
2588	31	MG	3130556	IMBÉ DE MINAS
2589	31	MG	3130606	INCONFIDENTES
2590	31	MG	3130655	INDAIABIRA
2591	31	MG	3130705	INDIANÓPOLIS
2592	31	MG	3130804	INGAÍ
2593	31	MG	3130903	INHAPIM
2594	31	MG	3131000	INHAÚMA
2595	31	MG	3131109	INIMUTABA
2596	31	MG	3131158	IPABA
2597	31	MG	3131208	IPANEMA
2598	31	MG	3131307	IPATINGA
2599	31	MG	3131406	IPIAÇU
2600	31	MG	3131505	IPUIÚNA
2601	31	MG	3131604	IRAÍ DE MINAS
2602	31	MG	3131703	ITABIRA
2603	31	MG	3131802	ITABIRINHA DE MANTENA
2604	31	MG	3131901	ITABIRITO
2605	31	MG	3132008	ITACAMBIRA
2606	31	MG	3132107	ITACARAMBI
2607	31	MG	3132206	ITAGUARA
2608	31	MG	3132305	ITAIPÉ
2609	31	MG	3132404	ITAJUBÁ
2610	31	MG	3132503	ITAMARANDIBA
2611	31	MG	3132602	ITAMARATI DE MINAS
2612	31	MG	3132701	ITAMBACURI
2613	31	MG	3132800	ITAMBÉ DO MATO DENTRO
2614	31	MG	3132909	ITAMOGI
2615	31	MG	3133006	ITAMONTE
2616	31	MG	3133105	ITANHANDU
2617	31	MG	3133204	ITANHOMI
2618	31	MG	3133303	ITAOBIM
2619	31	MG	3133402	ITAPAGIPE
2620	31	MG	3133501	ITAPECERICA
2621	31	MG	3133600	ITAPEVA
2622	31	MG	3133709	ITATIAIUÇU
2623	31	MG	3133758	ITAÚ DE MINAS
2624	31	MG	3133808	ITAÚNA
2625	31	MG	3133907	ITAVERAVA
2626	31	MG	3134004	ITINGA
2627	31	MG	3134103	ITUETA
2628	31	MG	3134202	ITUIUTABA
2629	31	MG	3134301	ITUMIRIM
2630	31	MG	3134400	ITURAMA
2631	31	MG	3134509	ITUTINGA
2632	31	MG	3134608	JABOTICATUBAS
2633	31	MG	3134707	JACINTO
2634	31	MG	3134806	JACUÍ
2635	31	MG	3134905	JACUTINGA
2636	31	MG	3135001	JAGUARAÇU
2637	31	MG	3135050	JAÍBA
2638	31	MG	3135076	JAMPRUCA
2639	31	MG	3135100	JANAÚBA
2640	31	MG	3135209	JANUÁRIA
2641	31	MG	3135308	JAPARAÍBA
2642	31	MG	3135357	JAPONVAR
2643	31	MG	3135407	JECEABA
2644	31	MG	3135456	JENIPAPO DE MINAS
2645	31	MG	3135506	JEQUERI
2646	31	MG	3135605	JEQUITAÍ
2647	31	MG	3135704	JEQUITIBÁ
2648	31	MG	3135803	JEQUITINHONHA
2649	31	MG	3135902	JESUÂNIA
2650	31	MG	3136009	JOAÍMA
2651	31	MG	3136108	JOANÉSIA
2652	31	MG	3136207	JOÃO MONLEVADE
2653	31	MG	3136306	JOÃO PINHEIRO
2654	31	MG	3136405	JOAQUIM FELÍCIO
2655	31	MG	3136504	JORDÂNIA
2656	31	MG	3136520	JOSÉ GONÇALVES DE MINAS
2657	31	MG	3136553	JOSÉ RAYDAN
2658	31	MG	3136579	JOSENÓPOLIS
2659	31	MG	3136603	NOVA UNIÃO
2660	31	MG	3136652	JUATUBA
2661	31	MG	3136702	JUIZ DE FORA
2662	31	MG	3136801	JURAMENTO
2663	31	MG	3136900	JURUAIA
2664	31	MG	3136959	JUVENÍLIA
2665	31	MG	3137007	LADAINHA
2666	31	MG	3137106	LAGAMAR
2667	31	MG	3137205	LAGOA DA PRATA
2668	31	MG	3137304	LAGOA DOS PATOS
2669	31	MG	3137403	LAGOA DOURADA
2670	31	MG	3137502	LAGOA FORMOSA
2671	31	MG	3137536	LAGOA GRANDE
2672	31	MG	3137601	LAGOA SANTA
2673	31	MG	3137700	LAJINHA
2674	31	MG	3137809	LAMBARI
2675	31	MG	3137908	LAMIM
2676	31	MG	3138005	LARANJAL
2677	31	MG	3138104	LASSANCE
2678	31	MG	3138203	LAVRAS
2679	31	MG	3138302	LEANDRO FERREIRA
2680	31	MG	3138351	LEME DO PRADO
2681	31	MG	3138401	LEOPOLDINA
2682	31	MG	3138500	LIBERDADE
2683	31	MG	3138609	LIMA DUARTE
2684	31	MG	3138625	LIMEIRA DO OESTE
2685	31	MG	3138658	LONTRA
2686	31	MG	3138674	LUISBURGO
2687	31	MG	3138682	LUISLÂNDIA
2688	31	MG	3138708	LUMINÁRIAS
2689	31	MG	3138807	LUZ
2690	31	MG	3138906	MACHACALIS
2691	31	MG	3139003	MACHADO
2692	31	MG	3139102	MADRE DE DEUS DE MINAS
2693	31	MG	3139201	MALACACHETA
2694	31	MG	3139250	MAMONAS
2695	31	MG	3139300	MANGA
2696	31	MG	3139409	MANHUAÇU
2697	31	MG	3139508	MANHUMIRIM
2698	31	MG	3139607	MANTENA
2699	31	MG	3139706	MARAVILHAS
2700	31	MG	3139805	MAR DE ESPANHA
2701	31	MG	3139904	MARIA DA FÉ
2702	31	MG	3140001	MARIANA
2703	31	MG	3140100	MARILAC
2704	31	MG	3140159	MÁRIO CAMPOS
2705	31	MG	3140209	MARIPÁ DE MINAS
2706	31	MG	3140308	MARLIÉRIA
2707	31	MG	3140407	MARMELÓPOLIS
2708	31	MG	3140506	MARTINHO CAMPOS
2709	31	MG	3140530	MARTINS SOARES
2710	31	MG	3140555	MATA VERDE
2711	31	MG	3140605	MATERLÂNDIA
2712	31	MG	3140704	MATEUS LEME
2713	31	MG	3140803	MATIAS BARBOSA
2714	31	MG	3140852	MATIAS CARDOSO
2715	31	MG	3140902	MATIPÓ
2716	31	MG	3141009	MATO VERDE
2717	31	MG	3141108	MATOZINHOS
2718	31	MG	3141207	MATUTINA
2719	31	MG	3141306	MEDEIROS
2720	31	MG	3141405	MEDINA
2721	31	MG	3141504	MENDES PIMENTEL
2722	31	MG	3141603	MERCÊS
2723	31	MG	3141702	MESQUITA
2724	31	MG	3141801	MINAS NOVAS
2725	31	MG	3141900	MINDURI
2726	31	MG	3142007	MIRABELA
2727	31	MG	3142106	MIRADOURO
2728	31	MG	3142205	MIRAÍ
2729	31	MG	3142254	MIRAVÂNIA
2730	31	MG	3142304	MOEDA
2731	31	MG	3142403	MOEMA
2732	31	MG	3142502	MONJOLOS
2733	31	MG	3142601	MONSENHOR PAULO
2734	31	MG	3142700	MONTALVÂNIA
2735	31	MG	3142809	MONTE ALEGRE DE MINAS
2736	31	MG	3142908	MONTE AZUL
2737	31	MG	3143005	MONTE BELO
2738	31	MG	3143104	MONTE CARMELO
2739	31	MG	3143153	MONTE FORMOSO
2740	31	MG	3143203	MONTE SANTO DE MINAS
2741	31	MG	3143302	MONTES CLAROS
2742	31	MG	3143401	MONTE SIÃO
2743	31	MG	3143450	MONTEZUMA
2744	31	MG	3143500	MORADA NOVA DE MINAS
2745	31	MG	3143609	MORRO DA GARÇA
2746	31	MG	3143708	MORRO DO PILAR
2747	31	MG	3143807	MUNHOZ
2748	31	MG	3143906	MURIAÉ
2749	31	MG	3144003	MUTUM
2750	31	MG	3144102	MUZAMBINHO
2751	31	MG	3144201	NACIP RAYDAN
2752	31	MG	3144300	NANUQUE
2753	31	MG	3144359	NAQUE
2754	31	MG	3144375	NATALÂNDIA
2755	31	MG	3144409	NATÉRCIA
2756	31	MG	3144508	NAZARENO
2757	31	MG	3144607	NEPOMUCENO
2758	31	MG	3144656	NINHEIRA
2759	31	MG	3144672	NOVA BELÉM
2760	31	MG	3144706	NOVA ERA
2761	31	MG	3144805	NOVA LIMA
2762	31	MG	3144904	NOVA MÓDICA
2763	31	MG	3145000	NOVA PONTE
2764	31	MG	3145059	NOVA PORTEIRINHA
2765	31	MG	3145109	NOVA RESENDE
2766	31	MG	3145208	NOVA SERRANA
2767	31	MG	3145307	NOVO CRUZEIRO
2768	31	MG	3145356	NOVO ORIENTE DE MINAS
2769	31	MG	3145372	NOVORIZONTE
2770	31	MG	3145406	OLARIA
2771	31	MG	3145455	OLHOS-D ÁGUA
2772	31	MG	3145505	OLÍMPIO NORONHA
2773	31	MG	3145604	OLIVEIRA
2774	31	MG	3145703	OLIVEIRA FORTES
2775	31	MG	3145802	ONÇA DE PITANGUI
2776	31	MG	3145851	ORATÓRIOS
2777	31	MG	3145877	ORIZÂNIA
2778	31	MG	3145901	OURO BRANCO
2779	31	MG	3146008	OURO FINO
2780	31	MG	3146107	OURO PRETO
2781	31	MG	3146206	OURO VERDE DE MINAS
2782	31	MG	3146255	PADRE CARVALHO
2783	31	MG	3146305	PADRE PARAÍSO
2784	31	MG	3146404	PAINEIRAS
2785	31	MG	3146503	PAINS
2786	31	MG	3146552	PAI PEDRO
2787	31	MG	3146602	PAIVA
2788	31	MG	3146701	PALMA
2789	31	MG	3146750	PALMÓPOLIS
2790	31	MG	3146909	PAPAGAIOS
2791	31	MG	3147006	PARACATU
2792	31	MG	3147105	PARÁ DE MINAS
2793	31	MG	3147204	PARAGUAÇU
2794	31	MG	3147303	PARAISÓPOLIS
2795	31	MG	3147402	PARAOPEBA
2796	31	MG	3147501	PASSABÉM
2797	31	MG	3147600	PASSA QUATRO
2798	31	MG	3147709	PASSA TEMPO
2799	31	MG	3147808	PASSA-VINTE
2800	31	MG	3147907	PASSOS
2801	31	MG	3147956	PATIS
2802	31	MG	3148004	PATOS DE MINAS
2803	31	MG	3148103	PATROCÍNIO
2804	31	MG	3148202	PATROCÍNIO DO MURIAÉ
2805	31	MG	3148301	PAULA CÂNDIDO
2806	31	MG	3148400	PAULISTAS
2807	31	MG	3148509	PAVÃO
2808	31	MG	3148608	PEÇANHA
2809	31	MG	3148707	PEDRA AZUL
2810	31	MG	3148756	PEDRA BONITA
2811	31	MG	3148806	PEDRA DO ANTA
2812	31	MG	3148905	PEDRA DO INDAIÁ
2813	31	MG	3149002	PEDRA DOURADA
2814	31	MG	3149101	PEDRALVA
2815	31	MG	3149150	PEDRAS DE MARIA DA CRUZ
2816	31	MG	3149200	PEDRINÓPOLIS
2817	31	MG	3149309	PEDRO LEOPOLDO
2818	31	MG	3149408	PEDRO TEIXEIRA
2819	31	MG	3149507	PEQUERI
2820	31	MG	3149606	PEQUI
2821	31	MG	3149705	PERDIGÃO
2822	31	MG	3149804	PERDIZES
2823	31	MG	3149903	PERDÕES
2824	31	MG	3149952	PERIQUITO
2825	31	MG	3150000	PESCADOR
2826	31	MG	3150109	PIAU
2827	31	MG	3150158	PIEDADE DE CARATINGA
2828	31	MG	3150208	PIEDADE DE PONTE NOVA
2829	31	MG	3150307	PIEDADE DO RIO GRANDE
2830	31	MG	3150406	PIEDADE DOS GERAIS
2831	31	MG	3150505	PIMENTA
2832	31	MG	3150539	PINGO-D ÁGUA
2833	31	MG	3150570	PINTÓPOLIS
2834	31	MG	3150604	PIRACEMA
2835	31	MG	3150703	PIRAJUBA
2836	31	MG	3150802	PIRANGA
2837	31	MG	3150901	PIRANGUÇU
2838	31	MG	3151008	PIRANGUINHO
2839	31	MG	3151107	PIRAPETINGA
2840	31	MG	3151206	PIRAPORA
2841	31	MG	3151305	PIRAÚBA
2842	31	MG	3151404	PITANGUI
2843	31	MG	3151503	PIUMHI
2844	31	MG	3151602	PLANURA
2845	31	MG	3151701	POÇO FUNDO
2846	31	MG	3151800	POÇOS DE CALDAS
2847	31	MG	3151909	POCRANE
2848	31	MG	3152006	POMPÉU
2849	31	MG	3152105	PONTE NOVA
2850	31	MG	3152131	PONTO CHIQUE
2851	31	MG	3152170	PONTO DOS VOLANTES
2852	31	MG	3152204	PORTEIRINHA
2853	31	MG	3152303	PORTO FIRME
2854	31	MG	3152402	POTÉ
2855	31	MG	3152501	POUSO ALEGRE
2856	31	MG	3152600	POUSO ALTO
2857	31	MG	3152709	PRADOS
2858	31	MG	3152808	PRATA
2859	31	MG	3152907	PRATÁPOLIS
2860	31	MG	3153004	PRATINHA
2861	31	MG	3153103	PRESIDENTE BERNARDES
2862	31	MG	3153202	PRESIDENTE JUSCELINO
2863	31	MG	3153301	PRESIDENTE KUBITSCHEK
2864	31	MG	3153400	PRESIDENTE OLEGÁRIO
2865	31	MG	3153509	ALTO JEQUITIBÁ
2866	31	MG	3153608	PRUDENTE DE MORAIS
2867	31	MG	3153707	QUARTEL GERAL
2868	31	MG	3153806	QUELUZITO
2869	31	MG	3153905	RAPOSOS
2870	31	MG	3154002	RAUL SOARES
2871	31	MG	3154101	RECREIO
2872	31	MG	3154150	REDUTO
2873	31	MG	3154200	RESENDE COSTA
2874	31	MG	3154309	RESPLENDOR
2875	31	MG	3154408	RESSAQUINHA
2876	31	MG	3154457	RIACHINHO
2877	31	MG	3154507	RIACHO DOS MACHADOS
2878	31	MG	3154606	RIBEIRÃO DAS NEVES
2879	31	MG	3154705	RIBEIRÃO VERMELHO
2880	31	MG	3154804	RIO ACIMA
2881	31	MG	3154903	RIO CASCA
2882	31	MG	3155009	RIO DOCE
2883	31	MG	3155108	RIO DO PRADO
2884	31	MG	3155207	RIO ESPERA
2885	31	MG	3155306	RIO MANSO
2886	31	MG	3155405	RIO NOVO
2887	31	MG	3155504	RIO PARANAÍBA
2888	31	MG	3155603	RIO PARDO DE MINAS
2889	31	MG	3155702	RIO PIRACICABA
2890	31	MG	3155801	RIO POMBA
2891	31	MG	3155900	RIO PRETO
2892	31	MG	3156007	RIO VERMELHO
2893	31	MG	3156106	RITÁPOLIS
2894	31	MG	3156205	ROCHEDO DE MINAS
2895	31	MG	3156304	RODEIRO
2896	31	MG	3156403	ROMARIA
2897	31	MG	3156452	ROSÁRIO DA LIMEIRA
2898	31	MG	3156502	RUBELITA
2899	31	MG	3156601	RUBIM
2900	31	MG	3156700	SABARÁ
2901	31	MG	3156809	SABINÓPOLIS
2902	31	MG	3156908	SACRAMENTO
2903	31	MG	3157005	SALINAS
2904	31	MG	3157104	SALTO DA DIVISA
2905	31	MG	3157203	SANTA BÁRBARA
2906	31	MG	3157252	SANTA BÁRBARA DO LESTE
2907	31	MG	3157278	SANTA BÁRBARA DO MONTE VERDE
2908	31	MG	3157302	SANTA BÁRBARA DO TUGÚRIO
2909	31	MG	3157336	SANTA CRUZ DE MINAS
2910	31	MG	3157377	SANTA CRUZ DE SALINAS
2911	31	MG	3157401	SANTA CRUZ DO ESCALVADO
2912	31	MG	3157500	SANTA EFIGÊNIA DE MINAS
2913	31	MG	3157609	SANTA FÉ DE MINAS
2914	31	MG	3157658	SANTA HELENA DE MINAS
2915	31	MG	3157708	SANTA JULIANA
2916	31	MG	3157807	SANTA LUZIA
2917	31	MG	3157906	SANTA MARGARIDA
2918	31	MG	3158003	SANTA MARIA DE ITABIRA
2919	31	MG	3158102	SANTA MARIA DO SALTO
2920	31	MG	3158201	SANTA MARIA DO SUAÇUÍ
2921	31	MG	3158300	SANTANA DA VARGEM
2922	31	MG	3158409	SANTANA DE CATAGUASES
2923	31	MG	3158508	SANTANA DE PIRAPAMA
2924	31	MG	3158607	SANTANA DO DESERTO
2925	31	MG	3158706	SANTANA DO GARAMBÉU
2926	31	MG	3158805	SANTANA DO JACARÉ
2927	31	MG	3158904	SANTANA DO MANHUAÇU
2928	31	MG	3158953	SANTANA DO PARAÍSO
2929	31	MG	3159001	SANTANA DO RIACHO
2930	31	MG	3159100	SANTANA DOS MONTES
2931	31	MG	3159209	SANTA RITA DE CALDAS
2932	31	MG	3159308	SANTA RITA DE JACUTINGA
2933	31	MG	3159357	SANTA RITA DE MINAS
2934	31	MG	3159407	SANTA RITA DE IBITIPOCA
2935	31	MG	3159506	SANTA RITA DO ITUETO
2936	31	MG	3159605	SANTA RITA DO SAPUCAÍ
2937	31	MG	3159704	SANTA ROSA DA SERRA
2938	31	MG	3159803	SANTA VITÓRIA
2939	31	MG	3159902	SANTO ANTÔNIO DO AMPARO
2940	31	MG	3160009	SANTO ANTÔNIO DO AVENTUREIRO
2941	31	MG	3160108	SANTO ANTÔNIO DO GRAMA
2942	31	MG	3160207	SANTO ANTÔNIO DO ITAMBÉ
2943	31	MG	3160306	SANTO ANTÔNIO DO JACINTO
2944	31	MG	3160405	SANTO ANTÔNIO DO MONTE
2945	31	MG	3160454	SANTO ANTÔNIO DO RETIRO
2946	31	MG	3160504	SANTO ANTÔNIO DO RIO ABAIXO
2947	31	MG	3160603	SANTO HIPÓLITO
2948	31	MG	3160702	SANTOS DUMONT
2949	31	MG	3160801	SÃO BENTO ABADE
2950	31	MG	3160900	SÃO BRÁS DO SUAÇUÍ
2951	31	MG	3160959	SÃO DOMINGOS DAS DORES
2952	31	MG	3161007	SÃO DOMINGOS DO PRATA
2953	31	MG	3161056	SÃO FÉLIX DE MINAS
2954	31	MG	3161106	SÃO FRANCISCO
2955	31	MG	3161205	SÃO FRANCISCO DE PAULA
2956	31	MG	3161304	SÃO FRANCISCO DE SALES
2957	31	MG	3161403	SÃO FRANCISCO DO GLÓRIA
2958	31	MG	3161502	SÃO GERALDO
2959	31	MG	3161601	SÃO GERALDO DA PIEDADE
2960	31	MG	3161650	SÃO GERALDO DO BAIXIO
2961	31	MG	3161700	SÃO GONÇALO DO ABAETÉ
2962	31	MG	3161809	SÃO GONÇALO DO PARÁ
2963	31	MG	3161908	SÃO GONÇALO DO RIO ABAIXO
2964	31	MG	3162005	SÃO GONÇALO DO SAPUCAÍ
2965	31	MG	3162104	SÃO GOTARDO
2966	31	MG	3162203	SÃO JOÃO BATISTA DO GLÓRIA
2967	31	MG	3162252	SÃO JOÃO DA LAGOA
2968	31	MG	3162302	SÃO JOÃO DA MATA
2969	31	MG	3162401	SÃO JOÃO DA PONTE
2970	31	MG	3162450	SÃO JOÃO DAS MISSÕES
2971	31	MG	3162500	SÃO JOÃO DEL REI
2972	31	MG	3162559	SÃO JOÃO DO MANHUAÇU
2973	31	MG	3162575	SÃO JOÃO DO MANTENINHA
2974	31	MG	3162609	SÃO JOÃO DO ORIENTE
2975	31	MG	3162658	SÃO JOÃO DO PACUÍ
2976	31	MG	3162708	SÃO JOÃO DO PARAÍSO
2977	31	MG	3162807	SÃO JOÃO EVANGELISTA
2978	31	MG	3162906	SÃO JOÃO NEPOMUCENO
2979	31	MG	3162922	SÃO JOAQUIM DE BICAS
2980	31	MG	3162948	SÃO JOSÉ DA BARRA
2981	31	MG	3162955	SÃO JOSÉ DA LAPA
2982	31	MG	3163003	SÃO JOSÉ DA SAFIRA
2983	31	MG	3163102	SÃO JOSÉ DA VARGINHA
2984	31	MG	3163201	SÃO JOSÉ DO ALEGRE
2985	31	MG	3163300	SÃO JOSÉ DO DIVINO
2986	31	MG	3163409	SÃO JOSÉ DO GOIABAL
2987	31	MG	3163508	SÃO JOSÉ DO JACURI
2988	31	MG	3163607	SÃO JOSÉ DO MANTIMENTO
2989	31	MG	3163706	SÃO LOURENÇO
2990	31	MG	3163805	SÃO MIGUEL DO ANTA
2991	31	MG	3163904	SÃO PEDRO DA UNIÃO
2992	31	MG	3164001	SÃO PEDRO DOS FERROS
2993	31	MG	3164100	SÃO PEDRO DO SUAÇUÍ
2994	31	MG	3164209	SÃO ROMÃO
2995	31	MG	3164308	SÃO ROQUE DE MINAS
2996	31	MG	3164407	SÃO SEBASTIÃO DA BELA VISTA
2997	31	MG	3164431	SÃO SEBASTIÃO DA VARGEM ALEGRE
2998	31	MG	3164472	SÃO SEBASTIÃO DO ANTA
2999	31	MG	3164506	SÃO SEBASTIÃO DO MARANHÃO
3000	31	MG	3164605	SÃO SEBASTIÃO DO OESTE
3001	31	MG	3164704	SÃO SEBASTIÃO DO PARAÍSO
3002	31	MG	3164803	SÃO SEBASTIÃO DO RIO PRETO
3003	31	MG	3164902	SÃO SEBASTIÃO DO RIO VERDE
3004	31	MG	3165008	SÃO TIAGO
3005	31	MG	3165107	SÃO TOMÁS DE AQUINO
3006	31	MG	3165206	SÃO THOMÉ DAS LETRAS
3007	31	MG	3165305	SÃO VICENTE DE MINAS
3008	31	MG	3165404	SAPUCAÍ-MIRIM
3009	31	MG	3165503	SARDOÁ
3010	31	MG	3165537	SARZEDO
3011	31	MG	3165552	SETUBINHA
3012	31	MG	3165560	SEM-PEIXE
3013	31	MG	3165578	SENADOR AMARAL
3014	31	MG	3165602	SENADOR CORTES
3015	31	MG	3165701	SENADOR FIRMINO
3016	31	MG	3165800	SENADOR JOSÉ BENTO
3017	31	MG	3165909	SENADOR MODESTINO GONÇALVES
3018	31	MG	3166006	SENHORA DE OLIVEIRA
3019	31	MG	3166105	SENHORA DO PORTO
3020	31	MG	3166204	SENHORA DOS REMÉDIOS
3021	31	MG	3166303	SERICITA
3022	31	MG	3166402	SERITINGA
3023	31	MG	3166501	SERRA AZUL DE MINAS
3024	31	MG	3166600	SERRA DA SAUDADE
3025	31	MG	3166709	SERRA DOS AIMORÉS
3026	31	MG	3166808	SERRA DO SALITRE
3027	31	MG	3166907	SERRANIA
3028	31	MG	3166956	SERRANÓPOLIS DE MINAS
3029	31	MG	3167004	SERRANOS
3030	31	MG	3167103	SERRO
3031	31	MG	3167202	SETE LAGOAS
3032	31	MG	3167301	SILVEIRÂNIA
3033	31	MG	3167400	SILVIANÓPOLIS
3034	31	MG	3167509	SIMÃO PEREIRA
3035	31	MG	3167608	SIMONÉSIA
3036	31	MG	3167707	SOBRÁLIA
3037	31	MG	3167806	SOLEDADE DE MINAS
3038	31	MG	3167905	TABULEIRO
3039	31	MG	3168002	TAIOBEIRAS
3040	31	MG	3168051	TAPARUBA
3041	31	MG	3168101	TAPIRA
3042	31	MG	3168200	TAPIRAÍ
3043	31	MG	3168309	TAQUARAÇU DE MINAS
3044	31	MG	3168408	TARUMIRIM
3045	31	MG	3168507	TEIXEIRAS
3046	31	MG	3168606	TEÓFILO OTONI
3047	31	MG	3168705	TIMÓTEO
3048	31	MG	3168804	TIRADENTES
3049	31	MG	3168903	TIROS
3050	31	MG	3169000	TOCANTINS
3051	31	MG	3169059	TOCOS DO MOJI
3052	31	MG	3169109	TOLEDO
3053	31	MG	3169208	TOMBOS
3054	31	MG	3169307	TRÊS CORAÇÕES
3055	31	MG	3169356	TRÊS MARIAS
3056	31	MG	3169406	TRÊS PONTAS
3057	31	MG	3169505	TUMIRITINGA
3058	31	MG	3169604	TUPACIGUARA
3059	31	MG	3169703	TURMALINA
3060	31	MG	3169802	TURVOLÂNDIA
3061	31	MG	3169901	UBÁ
3062	31	MG	3170008	UBAÍ
3063	31	MG	3170057	UBAPORANGA
3064	31	MG	3170107	UBERABA
3065	31	MG	3170206	UBERLÂNDIA
3066	31	MG	3170305	UMBURATIBA
3067	31	MG	3170404	UNAÍ
3068	31	MG	3170438	UNIÃO DE MINAS
3069	31	MG	3170479	URUANA DE MINAS
3070	31	MG	3170503	URUCÂNIA
3071	31	MG	3170529	URUCUIA
3072	31	MG	3170578	VARGEM ALEGRE
3073	31	MG	3170602	VARGEM BONITA
3074	31	MG	3170651	VARGEM GRANDE DO RIO PARDO
3075	31	MG	3170701	VARGINHA
3076	31	MG	3170750	VARJÃO DE MINAS
3077	31	MG	3170800	VÁRZEA DA PALMA
3078	31	MG	3170909	VARZELÂNDIA
3079	31	MG	3171006	VAZANTE
3080	31	MG	3171030	VERDELÂNDIA
3081	31	MG	3171071	VEREDINHA
3082	31	MG	3171105	VERÍSSIMO
3083	31	MG	3171154	VERMELHO NOVO
3084	31	MG	3171204	VESPASIANO
3085	31	MG	3171303	VIÇOSA
3086	31	MG	3171402	VIEIRAS
3087	31	MG	3171501	MATHIAS LOBATO
3088	31	MG	3171600	VIRGEM DA LAPA
3089	31	MG	3171709	VIRGÍNIA
3090	31	MG	3171808	VIRGINÓPOLIS
3091	31	MG	3171907	VIRGOLÂNDIA
3092	31	MG	3172004	VISCONDE DO RIO BRANCO
3093	31	MG	3172103	VOLTA GRANDE
3094	31	MG	3172202	WENCESLAU BRAZ
3095	32	ES	3200102	AFONSO CLÁUDIO
3096	32	ES	3200136	ÁGUIA BRANCA
3097	32	ES	3200169	ÁGUA DOCE DO NORTE
3098	32	ES	3200201	ALEGRE
3099	32	ES	3200300	ALFREDO CHAVES
3100	32	ES	3200359	ALTO RIO NOVO
3101	32	ES	3200409	ANCHIETA
3102	32	ES	3200508	APIACÁ
3103	32	ES	3200607	ARACRUZ
3104	32	ES	3200706	ATILIO VIVACQUA
3105	32	ES	3200805	BAIXO GUANDU
3106	32	ES	3200904	BARRA DE SÃO FRANCISCO
3107	32	ES	3201001	BOA ESPERANÇA
3108	32	ES	3201100	BOM JESUS DO NORTE
3109	32	ES	3201159	BREJETUBA
3110	32	ES	3201209	CACHOEIRO DE ITAPEMIRIM
3111	32	ES	3201308	CARIACICA
3112	32	ES	3201407	CASTELO
3113	32	ES	3201506	COLATINA
3114	32	ES	3201605	CONCEIÇÃO DA BARRA
3115	32	ES	3201704	CONCEIÇÃO DO CASTELO
3116	32	ES	3201803	DIVINO DE SÃO LOURENÇO
3117	32	ES	3201902	DOMINGOS MARTINS
3118	32	ES	3202009	DORES DO RIO PRETO
3119	32	ES	3202108	ECOPORANGA
3120	32	ES	3202207	FUNDÃO
3121	32	ES	3202256	GOVERNADOR LINDENBERG
3122	32	ES	3202306	GUAÇUÍ
3123	32	ES	3202405	GUARAPARI
3124	32	ES	3202454	IBATIBA
3125	32	ES	3202504	IBIRAÇU
3126	32	ES	3202553	IBITIRAMA
3127	32	ES	3202603	ICONHA
3128	32	ES	3202652	IRUPI
3129	32	ES	3202702	ITAGUAÇU
3130	32	ES	3202801	ITAPEMIRIM
3131	32	ES	3202900	ITARANA
3132	32	ES	3203007	IÚNA
3133	32	ES	3203056	JAGUARÉ
3134	32	ES	3203106	JERÔNIMO MONTEIRO
3135	32	ES	3203130	JOÃO NEIVA
3136	32	ES	3203163	LARANJA DA TERRA
3137	32	ES	3203205	LINHARES
3138	32	ES	3203304	MANTENÓPOLIS
3139	32	ES	3203320	MARATAÍZES
3140	32	ES	3203346	MARECHAL FLORIANO
3141	32	ES	3203353	MARILÂNDIA
3142	32	ES	3203403	MIMOSO DO SUL
3143	32	ES	3203502	MONTANHA
3144	32	ES	3203601	MUCURICI
3145	32	ES	3203700	MUNIZ FREIRE
3146	32	ES	3203809	MUQUI
3147	32	ES	3203908	NOVA VENÉCIA
3148	32	ES	3204005	PANCAS
3149	32	ES	3204054	PEDRO CANÁRIO
3150	32	ES	3204104	PINHEIROS
3151	32	ES	3204203	PIÚMA
3152	32	ES	3204252	PONTO BELO
3153	32	ES	3204302	PRESIDENTE KENNEDY
3154	32	ES	3204351	RIO BANANAL
3155	32	ES	3204401	RIO NOVO DO SUL
3156	32	ES	3204500	SANTA LEOPOLDINA
3157	32	ES	3204559	SANTA MARIA DE JETIBÁ
3158	32	ES	3204609	SANTA TERESA
3159	32	ES	3204658	SÃO DOMINGOS DO NORTE
3160	32	ES	3204708	SÃO GABRIEL DA PALHA
3161	32	ES	3204807	SÃO JOSÉ DO CALÇADO
3162	32	ES	3204906	SÃO MATEUS
3163	32	ES	3204955	SÃO ROQUE DO CANAÃ
3164	32	ES	3205002	SERRA
3165	32	ES	3205010	SOORETAMA
3166	32	ES	3205036	VARGEM ALTA
3167	32	ES	3205069	VENDA NOVA DO IMIGRANTE
3168	32	ES	3205101	VIANA
3169	32	ES	3205150	VILA PAVÃO
3170	32	ES	3205176	VILA VALÉRIO
3171	32	ES	3205200	VILA VELHA
3172	32	ES	3205309	VITÓRIA
3173	33	RJ	3300100	ANGRA DOS REIS
3174	33	RJ	3300159	APERIBÉ
3175	33	RJ	3300209	ARARUAMA
3176	33	RJ	3300225	AREAL
3177	33	RJ	3300233	ARMAÇÃO DOS BÚZIOS
3178	33	RJ	3300258	ARRAIAL DO CABO
3179	33	RJ	3300308	BARRA DO PIRAÍ
3180	33	RJ	3300407	BARRA MANSA
3181	33	RJ	3300456	BELFORD ROXO
3182	33	RJ	3300506	BOM JARDIM
3183	33	RJ	3300605	BOM JESUS DO ITABAPOANA
3184	33	RJ	3300704	CABO FRIO
3185	33	RJ	3300803	CACHOEIRAS DE MACACU
3186	33	RJ	3300902	CAMBUCI
3187	33	RJ	3300936	CARAPEBUS
3188	33	RJ	3300951	COMENDADOR LEVY GASPARIAN
3189	33	RJ	3301009	CAMPOS DOS GOYTACAZES
3190	33	RJ	3301108	CANTAGALO
3191	33	RJ	3301157	CARDOSO MOREIRA
3192	33	RJ	3301207	CARMO
3193	33	RJ	3301306	CASIMIRO DE ABREU
3194	33	RJ	3301405	CONCEIÇÃO DE MACABU
3195	33	RJ	3301504	CORDEIRO
3196	33	RJ	3301603	DUAS BARRAS
3197	33	RJ	3301702	DUQUE DE CAXIAS
3198	33	RJ	3301801	ENGENHEIRO PAULO DE FRONTIN
3199	33	RJ	3301850	GUAPIMIRIM
3200	33	RJ	3301876	IGUABA GRANDE
3201	33	RJ	3301900	ITABORAÍ
3202	33	RJ	3302007	ITAGUAÍ
3203	33	RJ	3302056	ITALVA
3204	33	RJ	3302106	ITAOCARA
3205	33	RJ	3302205	ITAPERUNA
3206	33	RJ	3302254	ITATIAIA
3207	33	RJ	3302270	JAPERI
3208	33	RJ	3302304	LAJE DO MURIAÉ
3209	33	RJ	3302403	MACAÉ (*)
3210	33	RJ	3302452	MACUCO
3211	33	RJ	3302502	MAGÉ
3212	33	RJ	3302601	MANGARATIBA
3213	33	RJ	3302700	MARICÁ
3214	33	RJ	3302809	MENDES
3215	33	RJ	3302858	MESQUITA
3216	33	RJ	3302908	MIGUEL PEREIRA
3217	33	RJ	3303005	MIRACEMA
3218	33	RJ	3303104	NATIVIDADE
3219	33	RJ	3303203	NILÓPOLIS
3220	33	RJ	3303302	NITERÓI
3221	33	RJ	3303401	NOVA FRIBURGO
3222	33	RJ	3303500	NOVA IGUAÇU
3223	33	RJ	3303609	PARACAMBI
3224	33	RJ	3303708	PARAÍBA DO SUL
3225	33	RJ	3303807	PARATI
3226	33	RJ	3303856	PATY DO ALFERES
3227	33	RJ	3303906	PETRÓPOLIS
3228	33	RJ	3303955	PINHEIRAL
3229	33	RJ	3304003	PIRAÍ
3230	33	RJ	3304102	PORCIÚNCULA
3231	33	RJ	3304110	PORTO REAL
3232	33	RJ	3304128	QUATIS
3233	33	RJ	3304144	QUEIMADOS
3234	33	RJ	3304151	QUISSAMÃ
3235	33	RJ	3304201	RESENDE
3236	33	RJ	3304300	RIO BONITO
3237	33	RJ	3304409	RIO CLARO
3238	33	RJ	3304508	RIO DAS FLORES
3239	33	RJ	3304524	RIO DAS OSTRAS
3240	33	RJ	3304557	RIO DE JANEIRO
3241	33	RJ	3304607	SANTA MARIA MADALENA
3242	33	RJ	3304706	SANTO ANTÔNIO DE PÁDUA
3243	33	RJ	3304755	SÃO FRANCISCO DE ITABAPOANA
3244	33	RJ	3304805	SÃO FIDÉLIS
3245	33	RJ	3304904	SÃO GONÇALO
3246	33	RJ	3305000	SÃO JOÃO DA BARRA
3247	33	RJ	3305109	SÃO JOÃO DE MERITI
3248	33	RJ	3305133	SÃO JOSÉ DE UBÁ
3249	33	RJ	3305158	SÃO JOSÉ DO VALE DO RIO PRETO
3250	33	RJ	3305208	SÃO PEDRO DA ALDEIA
3251	33	RJ	3305307	SÃO SEBASTIÃO DO ALTO
3252	33	RJ	3305406	SAPUCAIA
3253	33	RJ	3305505	SAQUAREMA
3254	33	RJ	3305554	SEROPÉDICA
3255	33	RJ	3305604	SILVA JARDIM
3256	33	RJ	3305703	SUMIDOURO
3257	33	RJ	3305752	TANGUÁ
3258	33	RJ	3305802	TERESÓPOLIS
3259	33	RJ	3305901	TRAJANO DE MORAIS
3260	33	RJ	3306008	TRÊS RIOS
3261	33	RJ	3306107	VALENÇA
3262	33	RJ	3306156	VARRE-SAI
3263	33	RJ	3306206	VASSOURAS
3264	33	RJ	3306305	VOLTA REDONDA
3265	35	SP	3500105	ADAMANTINA
3266	35	SP	3500204	ADOLFO
3267	35	SP	3500303	AGUAÍ
3268	35	SP	3500402	ÁGUAS DA PRATA
3269	35	SP	3500501	ÁGUAS DE LINDÓIA
3270	35	SP	3500550	ÁGUAS DE SANTA BÁRBARA
3271	35	SP	3500600	ÁGUAS DE SÃO PEDRO
3272	35	SP	3500709	AGUDOS
3273	35	SP	3500758	ALAMBARI
3274	35	SP	3500808	ALFREDO MARCONDES
3275	35	SP	3500907	ALTAIR
3276	35	SP	3501004	ALTINÓPOLIS
3277	35	SP	3501103	ALTO ALEGRE
3278	35	SP	3501152	ALUMÍNIO
3279	35	SP	3501202	ÁLVARES FLORENCE
3280	35	SP	3501301	ÁLVARES MACHADO
3281	35	SP	3501400	ÁLVARO DE CARVALHO
3282	35	SP	3501509	ALVINLÂNDIA
3283	35	SP	3501608	AMERICANA
3284	35	SP	3501707	AMÉRICO BRASILIENSE
3285	35	SP	3501806	AMÉRICO DE CAMPOS
3286	35	SP	3501905	AMPARO
3287	35	SP	3502002	ANALÂNDIA
3288	35	SP	3502101	ANDRADINA
3289	35	SP	3502200	ANGATUBA
3290	35	SP	3502309	ANHEMBI
3291	35	SP	3502408	ANHUMAS
3292	35	SP	3502507	APARECIDA
3293	35	SP	3502606	APARECIDA D OESTE
3294	35	SP	3502705	APIAÍ
3295	35	SP	3502754	ARAÇARIGUAMA
3296	35	SP	3502804	ARAÇATUBA
3297	35	SP	3502903	ARAÇOIABA DA SERRA
3298	35	SP	3503000	ARAMINA
3299	35	SP	3503109	ARANDU
3300	35	SP	3503158	ARAPEÍ
3301	35	SP	3503208	ARARAQUARA
3302	35	SP	3503307	ARARAS
3303	35	SP	3503356	ARCO-ÍRIS
3304	35	SP	3503406	AREALVA
3305	35	SP	3503505	AREIAS
3306	35	SP	3503604	AREIÓPOLIS
3307	35	SP	3503703	ARIRANHA
3308	35	SP	3503802	ARTUR NOGUEIRA
3309	35	SP	3503901	ARUJÁ
3310	35	SP	3503950	ASPÁSIA
3311	35	SP	3504008	ASSIS
3312	35	SP	3504107	ATIBAIA
3313	35	SP	3504206	AURIFLAMA
3314	35	SP	3504305	AVAÍ
3315	35	SP	3504404	AVANHANDAVA
3316	35	SP	3504503	AVARÉ
3317	35	SP	3504602	BADY BASSITT
3318	35	SP	3504701	BALBINOS
3319	35	SP	3504800	BÁLSAMO
3320	35	SP	3504909	BANANAL
3321	35	SP	3505005	BARÃO DE ANTONINA
3322	35	SP	3505104	BARBOSA
3323	35	SP	3505203	BARIRI
3324	35	SP	3505302	BARRA BONITA
3325	35	SP	3505351	BARRA DO CHAPÉU
3326	35	SP	3505401	BARRA DO TURVO
3327	35	SP	3505500	BARRETOS
3328	35	SP	3505609	BARRINHA
3329	35	SP	3505708	BARUERI
3330	35	SP	3505807	BASTOS
3331	35	SP	3505906	BATATAIS
3332	35	SP	3506003	BAURU
3333	35	SP	3506102	BEBEDOURO
3334	35	SP	3506201	BENTO DE ABREU
3335	35	SP	3506300	BERNARDINO DE CAMPOS
3336	35	SP	3506359	BERTIOGA
3337	35	SP	3506409	BILAC
3338	35	SP	3506508	BIRIGUI
3339	35	SP	3506607	BIRITIBA-MIRIM
3340	35	SP	3506706	BOA ESPERANÇA DO SUL
3341	35	SP	3506805	BOCAINA
3342	35	SP	3506904	BOFETE
3343	35	SP	3507001	BOITUVA
3344	35	SP	3507100	BOM JESUS DOS PERDÕES
3345	35	SP	3507159	BOM SUCESSO DE ITARARÉ
3346	35	SP	3507209	BORÁ
3347	35	SP	3507308	BORACÉIA
3348	35	SP	3507407	BORBOREMA
3349	35	SP	3507456	BOREBI
3350	35	SP	3507506	BOTUCATU
3351	35	SP	3507605	BRAGANÇA PAULISTA
3352	35	SP	3507704	BRAÚNA
3353	35	SP	3507753	BREJO ALEGRE
3354	35	SP	3507803	BRODOWSKI
3355	35	SP	3507902	BROTAS
3356	35	SP	3508009	BURI
3357	35	SP	3508108	BURITAMA
3358	35	SP	3508207	BURITIZAL
3359	35	SP	3508306	CABRÁLIA PAULISTA
3360	35	SP	3508405	CABREÚVA
3361	35	SP	3508504	CAÇAPAVA
3362	35	SP	3508603	CACHOEIRA PAULISTA
3363	35	SP	3508702	CACONDE
3364	35	SP	3508801	CAFELÂNDIA
3365	35	SP	3508900	CAIABU
3366	35	SP	3509007	CAIEIRAS
3367	35	SP	3509106	CAIUÁ
3368	35	SP	3509205	CAJAMAR
3369	35	SP	3509254	CAJATI
3370	35	SP	3509304	CAJOBI
3371	35	SP	3509403	CAJURU
3372	35	SP	3509452	CAMPINA DO MONTE ALEGRE
3373	35	SP	3509502	CAMPINAS
3374	35	SP	3509601	CAMPO LIMPO PAULISTA
3375	35	SP	3509700	CAMPOS DO JORDÃO
3376	35	SP	3509809	CAMPOS NOVOS PAULISTA
3377	35	SP	3509908	CANANÉIA
3378	35	SP	3509957	CANAS
3379	35	SP	3510005	CÂNDIDO MOTA
3380	35	SP	3510104	CÂNDIDO RODRIGUES
3381	35	SP	3510153	CANITAR
3382	35	SP	3510203	CAPÃO BONITO
3383	35	SP	3510302	CAPELA DO ALTO
3384	35	SP	3510401	CAPIVARI
3385	35	SP	3510500	CARAGUATATUBA
3386	35	SP	3510609	CARAPICUÍBA
3387	35	SP	3510708	CARDOSO
3388	35	SP	3510807	CASA BRANCA
3389	35	SP	3510906	CÁSSIA DOS COQUEIROS
3390	35	SP	3511003	CASTILHO
3391	35	SP	3511102	CATANDUVA
3392	35	SP	3511201	CATIGUÁ
3393	35	SP	3511300	CEDRAL
3394	35	SP	3511409	CERQUEIRA CÉSAR
3395	35	SP	3511508	CERQUILHO
3396	35	SP	3511607	CESÁRIO LANGE
3397	35	SP	3511706	CHARQUEADA
3398	35	SP	3511904	CLEMENTINA
3399	35	SP	3512001	COLINA
3400	35	SP	3512100	COLÔMBIA
3401	35	SP	3512209	CONCHAL
3402	35	SP	3512308	CONCHAS
3403	35	SP	3512407	CORDEIRÓPOLIS
3404	35	SP	3512506	COROADOS
3405	35	SP	3512605	CORONEL MACEDO
3406	35	SP	3512704	CORUMBATAÍ
3407	35	SP	3512803	COSMÓPOLIS
3408	35	SP	3512902	COSMORAMA
3409	35	SP	3513009	COTIA
3410	35	SP	3513108	CRAVINHOS
3411	35	SP	3513207	CRISTAIS PAULISTA
3412	35	SP	3513306	CRUZÁLIA
3413	35	SP	3513405	CRUZEIRO
3414	35	SP	3513504	CUBATÃO
3415	35	SP	3513603	CUNHA
3416	35	SP	3513702	DESCALVADO
3417	35	SP	3513801	DIADEMA
3418	35	SP	3513850	DIRCE REIS
3419	35	SP	3513900	DIVINOLÂNDIA
3420	35	SP	3514007	DOBRADA
3421	35	SP	3514106	DOIS CÓRREGOS
3422	35	SP	3514205	DOLCINÓPOLIS
3423	35	SP	3514304	DOURADO
3424	35	SP	3514403	DRACENA
3425	35	SP	3514502	DUARTINA
3426	35	SP	3514601	DUMONT
3427	35	SP	3514700	ECHAPORÃ
3428	35	SP	3514809	ELDORADO
3429	35	SP	3514908	ELIAS FAUSTO
3430	35	SP	3514924	ELISIÁRIO
3431	35	SP	3514957	EMBAÚBA
3432	35	SP	3515004	EMBU
3433	35	SP	3515103	EMBU-GUAÇU
3434	35	SP	3515129	EMILIANÓPOLIS
3435	35	SP	3515152	ENGENHEIRO COELHO
3436	35	SP	3515186	ESPÍRITO SANTO DO PINHAL
3437	35	SP	3515194	ESPÍRITO SANTO DO TURVO
3438	35	SP	3515202	ESTRELA D OESTE
3439	35	SP	3515301	ESTRELA DO NORTE
3440	35	SP	3515350	EUCLIDES DA CUNHA PAULISTA
3441	35	SP	3515400	FARTURA
3442	35	SP	3515509	FERNANDÓPOLIS
3443	35	SP	3515608	FERNANDO PRESTES
3444	35	SP	3515657	FERNÃO
3445	35	SP	3515707	FERRAZ DE VASCONCELOS
3446	35	SP	3515806	FLORA RICA
3447	35	SP	3515905	FLOREAL
3448	35	SP	3516002	FLÓRIDA PAULISTA
3449	35	SP	3516101	FLORÍNIA
3450	35	SP	3516200	FRANCA
3451	35	SP	3516309	FRANCISCO MORATO
3452	35	SP	3516408	FRANCO DA ROCHA
3453	35	SP	3516507	GABRIEL MONTEIRO
3454	35	SP	3516606	GÁLIA
3455	35	SP	3516705	GARÇA
3456	35	SP	3516804	GASTÃO VIDIGAL
3457	35	SP	3516853	GAVIÃO PEIXOTO
3458	35	SP	3516903	GENERAL SALGADO
3459	35	SP	3517000	GETULINA
3460	35	SP	3517109	GLICÉRIO
3461	35	SP	3517208	GUAIÇARA
3462	35	SP	3517307	GUAIMBÊ
3463	35	SP	3517406	GUAÍRA
3464	35	SP	3517505	GUAPIAÇU
3465	35	SP	3517604	GUAPIARA
3466	35	SP	3517703	GUARÁ
3467	35	SP	3517802	GUARAÇAÍ
3468	35	SP	3517901	GUARACI
3469	35	SP	3518008	GUARANI D OESTE
3470	35	SP	3518107	GUARANTÃ
3471	35	SP	3518206	GUARARAPES
3472	35	SP	3518305	GUARAREMA
3473	35	SP	3518404	GUARATINGUETÁ
3474	35	SP	3518503	GUAREÍ
3475	35	SP	3518602	GUARIBA
3476	35	SP	3518701	GUARUJÁ
3477	35	SP	3518800	GUARULHOS
3478	35	SP	3518859	GUATAPARÁ
3479	35	SP	3518909	GUZOLÂNDIA
3480	35	SP	3519006	HERCULÂNDIA
3481	35	SP	3519055	HOLAMBRA
3482	35	SP	3519071	HORTOLÂNDIA
3483	35	SP	3519105	IACANGA
3484	35	SP	3519204	IACRI
3485	35	SP	3519253	IARAS
3486	35	SP	3519303	IBATÉ
3487	35	SP	3519402	IBIRÁ
3488	35	SP	3519501	IBIRAREMA
3489	35	SP	3519600	IBITINGA
3490	35	SP	3519709	IBIÚNA
3491	35	SP	3519808	ICÉM
3492	35	SP	3519907	IEPÊ
3493	35	SP	3520004	IGARAÇU DO TIETÊ
3494	35	SP	3520103	IGARAPAVA
3495	35	SP	3520202	IGARATÁ
3496	35	SP	3520301	IGUAPE
3497	35	SP	3520400	ILHABELA
3498	35	SP	3520426	ILHA COMPRIDA
3499	35	SP	3520442	ILHA SOLTEIRA
3500	35	SP	3520509	INDAIATUBA
3501	35	SP	3520608	INDIANA
3502	35	SP	3520707	INDIAPORÃ
3503	35	SP	3520806	INÚBIA PAULISTA
3504	35	SP	3520905	IPAUSSU
3505	35	SP	3521002	IPERÓ
3506	35	SP	3521101	IPEÚNA
3507	35	SP	3521150	IPIGUÁ
3508	35	SP	3521200	IPORANGA
3509	35	SP	3521309	IPUÃ
3510	35	SP	3521408	IRACEMÁPOLIS
3511	35	SP	3521507	IRAPUÃ
3512	35	SP	3521606	IRAPURU
3513	35	SP	3521705	ITABERÁ
3514	35	SP	3521804	ITAÍ
3515	35	SP	3521903	ITAJOBI
3516	35	SP	3522000	ITAJU
3517	35	SP	3522109	ITANHAÉM
3518	35	SP	3522158	ITAÓCA
3519	35	SP	3522208	ITAPECERICA DA SERRA
3520	35	SP	3522307	ITAPETININGA
3521	35	SP	3522406	ITAPEVA
3522	35	SP	3522505	ITAPEVI
3523	35	SP	3522604	ITAPIRA
3524	35	SP	3522653	ITAPIRAPUÃ PAULISTA
3525	35	SP	3522703	ITÁPOLIS
3526	35	SP	3522802	ITAPORANGA
3527	35	SP	3522901	ITAPUÍ
3528	35	SP	3523008	ITAPURA
3529	35	SP	3523107	ITAQUAQUECETUBA
3530	35	SP	3523206	ITARARÉ
3531	35	SP	3523305	ITARIRI
3532	35	SP	3523404	ITATIBA
3533	35	SP	3523503	ITATINGA
3534	35	SP	3523602	ITIRAPINA
3535	35	SP	3523701	ITIRAPUÃ
3536	35	SP	3523800	ITOBI
3537	35	SP	3523909	ITU
3538	35	SP	3524006	ITUPEVA
3539	35	SP	3524105	ITUVERAVA
3540	35	SP	3524204	JABORANDI
3541	35	SP	3524303	JABOTICABAL
3542	35	SP	3524402	JACAREÍ
3543	35	SP	3524501	JACI
3544	35	SP	3524600	JACUPIRANGA
3545	35	SP	3524709	JAGUARIÚNA
3546	35	SP	3524808	JALES
3547	35	SP	3524907	JAMBEIRO
3548	35	SP	3525003	JANDIRA
3549	35	SP	3525102	JARDINÓPOLIS
3550	35	SP	3525201	JARINU
3551	35	SP	3525300	JAÚ
3552	35	SP	3525409	JERIQUARA
3553	35	SP	3525508	JOANÓPOLIS
3554	35	SP	3525607	JOÃO RAMALHO
3555	35	SP	3525706	JOSÉ BONIFÁCIO
3556	35	SP	3525805	JÚLIO MESQUITA
3557	35	SP	3525854	JUMIRIM
3558	35	SP	3525904	JUNDIAÍ
3559	35	SP	3526001	JUNQUEIRÓPOLIS
3560	35	SP	3526100	JUQUIÁ
3561	35	SP	3526209	JUQUITIBA
3562	35	SP	3526308	LAGOINHA
3563	35	SP	3526407	LARANJAL PAULISTA
3564	35	SP	3526506	LAVÍNIA
3565	35	SP	3526605	LAVRINHAS
3566	35	SP	3526704	LEME
3567	35	SP	3526803	LENÇÓIS PAULISTA
3568	35	SP	3526902	LIMEIRA
3569	35	SP	3527009	LINDÓIA
3570	35	SP	3527108	LINS
3571	35	SP	3527207	LORENA
3572	35	SP	3527256	LOURDES
3573	35	SP	3527306	LOUVEIRA
3574	35	SP	3527405	LUCÉLIA
3575	35	SP	3527504	LUCIANÓPOLIS
3576	35	SP	3527603	LUÍS ANTÔNIO
3577	35	SP	3527702	LUIZIÂNIA
3578	35	SP	3527801	LUPÉRCIO
3579	35	SP	3527900	LUTÉCIA
3580	35	SP	3528007	MACATUBA
3581	35	SP	3528106	MACAUBAL
3582	35	SP	3528205	MACEDÔNIA
3583	35	SP	3528304	MAGDA
3584	35	SP	3528403	MAIRINQUE
3585	35	SP	3528502	MAIRIPORÃ
3586	35	SP	3528601	MANDURI
3587	35	SP	3528700	MARABÁ PAULISTA
3588	35	SP	3528809	MARACAÍ
3589	35	SP	3528858	MARAPOAMA
3590	35	SP	3528908	MARIÁPOLIS
3591	35	SP	3529005	MARÍLIA
3592	35	SP	3529104	MARINÓPOLIS
3593	35	SP	3529203	MARTINÓPOLIS
3594	35	SP	3529302	MATÃO
3595	35	SP	3529401	MAUÁ
3596	35	SP	3529500	MENDONÇA
3597	35	SP	3529609	MERIDIANO
3598	35	SP	3529658	MESÓPOLIS
3599	35	SP	3529708	MIGUELÓPOLIS
3600	35	SP	3529807	MINEIROS DO TIETÊ
3601	35	SP	3529906	MIRACATU
3602	35	SP	3530003	MIRA ESTRELA
3603	35	SP	3530102	MIRANDÓPOLIS
3604	35	SP	3530201	MIRANTE DO PARANAPANEMA
3605	35	SP	3530300	MIRASSOL
3606	35	SP	3530409	MIRASSOLÂNDIA
3607	35	SP	3530508	MOCOCA
3608	35	SP	3530607	MOJI DAS CRUZES
3609	35	SP	3530706	MOGI GUAÇU
3610	35	SP	3530805	MOJI-MIRIM
3611	35	SP	3530904	MOMBUCA
3612	35	SP	3531001	MONÇÕES
3613	35	SP	3531100	MONGAGUÁ
3614	35	SP	3531209	MONTE ALEGRE DO SUL
3615	35	SP	3531308	MONTE ALTO
3616	35	SP	3531407	MONTE APRAZÍVEL
3617	35	SP	3531506	MONTE AZUL PAULISTA
3618	35	SP	3531605	MONTE CASTELO
3619	35	SP	3531704	MONTEIRO LOBATO
3620	35	SP	3531803	MONTE MOR
3621	35	SP	3531902	MORRO AGUDO
3622	35	SP	3532009	MORUNGABA
3623	35	SP	3532058	MOTUCA
3624	35	SP	3532108	MURUTINGA DO SUL
3625	35	SP	3532157	NANTES
3626	35	SP	3532207	NARANDIBA
3627	35	SP	3532306	NATIVIDADE DA SERRA
3628	35	SP	3532405	NAZARÉ PAULISTA
3629	35	SP	3532504	NEVES PAULISTA
3630	35	SP	3532603	NHANDEARA
3631	35	SP	3532702	NIPOÃ
3632	35	SP	3532801	NOVA ALIANÇA
3633	35	SP	3532827	NOVA CAMPINA
3634	35	SP	3532843	NOVA CANAÃ PAULISTA
3635	35	SP	3532868	NOVA CASTILHO
3636	35	SP	3532900	NOVA EUROPA
3637	35	SP	3533007	NOVA GRANADA
3638	35	SP	3533106	NOVA GUATAPORANGA
3639	35	SP	3533205	NOVA INDEPENDÊNCIA
3640	35	SP	3533254	NOVAIS
3641	35	SP	3533304	NOVA LUZITÂNIA
3642	35	SP	3533403	NOVA ODESSA
3643	35	SP	3533502	NOVO HORIZONTE
3644	35	SP	3533601	NUPORANGA
3645	35	SP	3533700	OCAUÇU
3646	35	SP	3533809	ÓLEO
3647	35	SP	3533908	OLÍMPIA
3648	35	SP	3534005	ONDA VERDE
3649	35	SP	3534104	ORIENTE
3650	35	SP	3534203	ORINDIÚVA
3651	35	SP	3534302	ORLÂNDIA
3652	35	SP	3534401	OSASCO
3653	35	SP	3534500	OSCAR BRESSANE
3654	35	SP	3534609	OSVALDO CRUZ
3655	35	SP	3534708	OURINHOS
3656	35	SP	3534757	OUROESTE
3657	35	SP	3534807	OURO VERDE
3658	35	SP	3534906	PACAEMBU
3659	35	SP	3535002	PALESTINA
3660	35	SP	3535101	PALMARES PAULISTA
3661	35	SP	3535200	PALMEIRA D OESTE
3662	35	SP	3535309	PALMITAL
3663	35	SP	3535408	PANORAMA
3664	35	SP	3535507	PARAGUAÇU PAULISTA
3665	35	SP	3535606	PARAIBUNA
3666	35	SP	3535705	PARAÍSO
3667	35	SP	3535804	PARANAPANEMA
3668	35	SP	3535903	PARANAPUÃ
3669	35	SP	3536000	PARAPUÃ
3670	35	SP	3536109	PARDINHO
3671	35	SP	3536208	PARIQUERA-AÇU
3672	35	SP	3536257	PARISI
3673	35	SP	3536307	PATROCÍNIO PAULISTA
3674	35	SP	3536406	PAULICÉIA
3675	35	SP	3536505	PAULÍNIA
3676	35	SP	3536570	PAULISTÂNIA
3677	35	SP	3536604	PAULO DE FARIA
3678	35	SP	3536703	PEDERNEIRAS
3679	35	SP	3536802	PEDRA BELA
3680	35	SP	3536901	PEDRANÓPOLIS
3681	35	SP	3537008	PEDREGULHO
3682	35	SP	3537107	PEDREIRA
3683	35	SP	3537156	PEDRINHAS PAULISTA
3684	35	SP	3537206	PEDRO DE TOLEDO
3685	35	SP	3537305	PENÁPOLIS
3686	35	SP	3537404	PEREIRA BARRETO
3687	35	SP	3537503	PEREIRAS
3688	35	SP	3537602	PERUÍBE
3689	35	SP	3537701	PIACATU
3690	35	SP	3537800	PIEDADE
3691	35	SP	3537909	PILAR DO SUL
3692	35	SP	3538006	PINDAMONHANGABA
3693	35	SP	3538105	PINDORAMA
3694	35	SP	3538204	PINHALZINHO
3695	35	SP	3538303	PIQUEROBI
3696	35	SP	3538501	PIQUETE
3697	35	SP	3538600	PIRACAIA
3698	35	SP	3538709	PIRACICABA
3699	35	SP	3538808	PIRAJU
3700	35	SP	3538907	PIRAJUÍ
3701	35	SP	3539004	PIRANGI
3702	35	SP	3539103	PIRAPORA DO BOM JESUS
3703	35	SP	3539202	PIRAPOZINHO
3704	35	SP	3539301	PIRASSUNUNGA
3705	35	SP	3539400	PIRATININGA
3706	35	SP	3539509	PITANGUEIRAS
3707	35	SP	3539608	PLANALTO
3708	35	SP	3539707	PLATINA
3709	35	SP	3539806	POÁ
3710	35	SP	3539905	POLONI
3711	35	SP	3540002	POMPÉIA
3712	35	SP	3540101	PONGAÍ
3713	35	SP	3540200	PONTAL
3714	35	SP	3540259	PONTALINDA
3715	35	SP	3540309	PONTES GESTAL
3716	35	SP	3540408	POPULINA
3717	35	SP	3540507	PORANGABA
3718	35	SP	3540606	PORTO FELIZ
3719	35	SP	3540705	PORTO FERREIRA
3720	35	SP	3540754	POTIM
3721	35	SP	3540804	POTIRENDABA
3722	35	SP	3540853	PRACINHA
3723	35	SP	3540903	PRADÓPOLIS
3724	35	SP	3541000	PRAIA GRANDE
3725	35	SP	3541059	PRATÂNIA
3726	35	SP	3541109	PRESIDENTE ALVES
3727	35	SP	3541208	PRESIDENTE BERNARDES
3728	35	SP	3541307	PRESIDENTE EPITÁCIO
3729	35	SP	3541406	PRESIDENTE PRUDENTE
3730	35	SP	3541505	PRESIDENTE VENCESLAU
3731	35	SP	3541604	PROMISSÃO
3732	35	SP	3541653	QUADRA
3733	35	SP	3541703	QUATÁ
3734	35	SP	3541802	QUEIROZ
3735	35	SP	3541901	QUELUZ
3736	35	SP	3542008	QUINTANA
3737	35	SP	3542107	RAFARD
3738	35	SP	3542206	RANCHARIA
3739	35	SP	3542305	REDENÇÃO DA SERRA
3740	35	SP	3542404	REGENTE FEIJÓ
3741	35	SP	3542503	REGINÓPOLIS
3742	35	SP	3542602	REGISTRO
3743	35	SP	3542701	RESTINGA
3744	35	SP	3542800	RIBEIRA
3745	35	SP	3542909	RIBEIRÃO BONITO
3746	35	SP	3543006	RIBEIRÃO BRANCO
3747	35	SP	3543105	RIBEIRÃO CORRENTE
3748	35	SP	3543204	RIBEIRÃO DO SUL
3749	35	SP	3543238	RIBEIRÃO DOS ÍNDIOS
3750	35	SP	3543253	RIBEIRÃO GRANDE
3751	35	SP	3543303	RIBEIRÃO PIRES
3752	35	SP	3543402	RIBEIRÃO PRETO
3753	35	SP	3543501	RIVERSUL
3754	35	SP	3543600	RIFAINA
3755	35	SP	3543709	RINCÃO
3756	35	SP	3543808	RINÓPOLIS
3757	35	SP	3543907	RIO CLARO
3758	35	SP	3544004	RIO DAS PEDRAS
3759	35	SP	3544103	RIO GRANDE DA SERRA
3760	35	SP	3544202	RIOLÂNDIA
3761	35	SP	3544251	ROSANA
3762	35	SP	3544301	ROSEIRA
3763	35	SP	3544400	RUBIÁCEA
3764	35	SP	3544509	RUBINÉIA
3765	35	SP	3544608	SABINO
3766	35	SP	3544707	SAGRES
3767	35	SP	3544806	SALES
3768	35	SP	3544905	SALES OLIVEIRA
3769	35	SP	3545001	SALESÓPOLIS
3770	35	SP	3545100	SALMOURÃO
3771	35	SP	3545159	SALTINHO
3772	35	SP	3545209	SALTO
3773	35	SP	3545308	SALTO DE PIRAPORA
3774	35	SP	3545407	SALTO GRANDE
3775	35	SP	3545506	SANDOVALINA
3776	35	SP	3545605	SANTA ADÉLIA
3777	35	SP	3545704	SANTA ALBERTINA
3778	35	SP	3545803	SANTA BÁRBARA D OESTE
3779	35	SP	3546009	SANTA BRANCA
3780	35	SP	3546108	SANTA CLARA D OESTE
3781	35	SP	3546207	SANTA CRUZ DA CONCEIÇÃO
3782	35	SP	3546256	SANTA CRUZ DA ESPERANÇA
3783	35	SP	3546306	SANTA CRUZ DAS PALMEIRAS
3784	35	SP	3546405	SANTA CRUZ DO RIO PARDO
3785	35	SP	3546504	SANTA ERNESTINA
3786	35	SP	3546603	SANTA FÉ DO SUL
3787	35	SP	3546702	SANTA GERTRUDES
3788	35	SP	3546801	SANTA ISABEL
3789	35	SP	3546900	SANTA LÚCIA
3790	35	SP	3547007	SANTA MARIA DA SERRA
3791	35	SP	3547106	SANTA MERCEDES
3792	35	SP	3547205	SANTANA DA PONTE PENSA
3793	35	SP	3547304	SANTANA DE PARNAÍBA
3794	35	SP	3547403	SANTA RITA D OESTE
3795	35	SP	3547502	SANTA RITA DO PASSA QUATRO
3796	35	SP	3547601	SANTA ROSA DE VITERBO
3797	35	SP	3547650	SANTA SALETE
3798	35	SP	3547700	SANTO ANASTÁCIO
3799	35	SP	3547809	SANTO ANDRÉ
3800	35	SP	3547908	SANTO ANTÔNIO DA ALEGRIA
3801	35	SP	3548005	SANTO ANTÔNIO DE POSSE
3802	35	SP	3548054	SANTO ANTÔNIO DO ARACANGUÁ
3803	35	SP	3548104	SANTO ANTÔNIO DO JARDIM
3804	35	SP	3548203	SANTO ANTÔNIO DO PINHAL
3805	35	SP	3548302	SANTO EXPEDITO
3806	35	SP	3548401	SANTÓPOLIS DO AGUAPEÍ
3807	35	SP	3548500	SANTOS
3808	35	SP	3548609	SÃO BENTO DO SAPUCAÍ
3809	35	SP	3548708	SÃO BERNARDO DO CAMPO
3810	35	SP	3548807	SÃO CAETANO DO SUL
3811	35	SP	3548906	SÃO CARLOS
3812	35	SP	3549003	SÃO FRANCISCO
3813	35	SP	3549102	SÃO JOÃO DA BOA VISTA
3814	35	SP	3549201	SÃO JOÃO DAS DUAS PONTES
3815	35	SP	3549250	SÃO JOÃO DE IRACEMA
3816	35	SP	3549300	SÃO JOÃO DO PAU D ALHO
3817	35	SP	3549409	SÃO JOAQUIM DA BARRA
3818	35	SP	3549508	SÃO JOSÉ DA BELA VISTA
3819	35	SP	3549607	SÃO JOSÉ DO BARREIRO
3820	35	SP	3549706	SÃO JOSÉ DO RIO PARDO
3821	35	SP	3549805	SÃO JOSÉ DO RIO PRETO
3822	35	SP	3549904	SÃO JOSÉ DOS CAMPOS
3823	35	SP	3549953	SÃO LOURENÇO DA SERRA
3824	35	SP	3550001	SÃO LUÍS DO PARAITINGA
3825	35	SP	3550100	SÃO MANUEL
3826	35	SP	3550209	SÃO MIGUEL ARCANJO
3827	35	SP	3550308	SÃO PAULO
3828	35	SP	3550407	SÃO PEDRO
3829	35	SP	3550506	SÃO PEDRO DO TURVO
3830	35	SP	3550605	SÃO ROQUE
3831	35	SP	3550704	SÃO SEBASTIÃO
3832	35	SP	3550803	SÃO SEBASTIÃO DA GRAMA
3833	35	SP	3550902	SÃO SIMÃO
3834	35	SP	3551009	SÃO VICENTE
3835	35	SP	3551108	SARAPUÍ
3836	35	SP	3551207	SARUTAIÁ
3837	35	SP	3551306	SEBASTIANÓPOLIS DO SUL
3838	35	SP	3551405	SERRA AZUL
3839	35	SP	3551504	SERRANA
3840	35	SP	3551603	SERRA NEGRA
3841	35	SP	3551702	SERTÃOZINHO
3842	35	SP	3551801	SETE BARRAS
3843	35	SP	3551900	SEVERÍNIA
3844	35	SP	3552007	SILVEIRAS
3845	35	SP	3552106	SOCORRO
3846	35	SP	3552205	SOROCABA
3847	35	SP	3552304	SUD MENNUCCI
3848	35	SP	3552403	SUMARÉ
3849	35	SP	3552502	SUZANO
3850	35	SP	3552551	SUZANÁPOLIS
3851	35	SP	3552601	TABAPUÃ
3852	35	SP	3552700	TABATINGA
3853	35	SP	3552809	TABOÃO DA SERRA
3854	35	SP	3552908	TACIBA
3855	35	SP	3553005	TAGUAÍ
3856	35	SP	3553104	TAIAÇU
3857	35	SP	3553203	TAIÚVA
3858	35	SP	3553302	TAMBAÚ
3859	35	SP	3553401	TANABI
3860	35	SP	3553500	TAPIRAÍ
3861	35	SP	3553609	TAPIRATIBA
3862	35	SP	3553658	TAQUARAL
3863	35	SP	3553708	TAQUARITINGA
3864	35	SP	3553807	TAQUARITUBA
3865	35	SP	3553856	TAQUARIVAÍ
3866	35	SP	3553906	TARABAI
3867	35	SP	3553955	TARUMÃ
3868	35	SP	3554003	TATUÍ
3869	35	SP	3554102	TAUBATÉ
3870	35	SP	3554201	TEJUPÁ
3871	35	SP	3554300	TEODORO SAMPAIO
3872	35	SP	3554409	TERRA ROXA
3873	35	SP	3554508	TIETÊ
3874	35	SP	3554607	TIMBURI
3875	35	SP	3554656	TORRE DE PEDRA
3876	35	SP	3554706	TORRINHA
3877	35	SP	3554755	TRABIJU
3878	35	SP	3554805	TREMEMBÉ
3879	35	SP	3554904	TRÊS FRONTEIRAS
3880	35	SP	3554953	TUIUTI
3881	35	SP	3555000	TUPÃ
3882	35	SP	3555109	TUPI PAULISTA
3883	35	SP	3555208	TURIÚBA
3884	35	SP	3555307	TURMALINA
3885	35	SP	3555356	UBARANA
3886	35	SP	3555406	UBATUBA
3887	35	SP	3555505	UBIRAJARA
3888	35	SP	3555604	UCHOA
3889	35	SP	3555703	UNIÃO PAULISTA
3890	35	SP	3555802	URÂNIA
3891	35	SP	3555901	URU
3892	35	SP	3556008	URUPÊS
3893	35	SP	3556107	VALENTIM GENTIL
3894	35	SP	3556206	VALINHOS
3895	35	SP	3556305	VALPARAÍSO
3896	35	SP	3556354	VARGEM
3897	35	SP	3556404	VARGEM GRANDE DO SUL
3898	35	SP	3556453	VARGEM GRANDE PAULISTA
3899	35	SP	3556503	VÁRZEA PAULISTA
3900	35	SP	3556602	VERA CRUZ
3901	35	SP	3556701	VINHEDO
3902	35	SP	3556800	VIRADOURO
3903	35	SP	3556909	VISTA ALEGRE DO ALTO
3904	35	SP	3556958	VITÓRIA BRASIL
3905	35	SP	3557006	VOTORANTIM
3906	35	SP	3557105	VOTUPORANGA
3907	35	SP	3557154	ZACARIAS
3908	35	SP	3557204	CHAVANTES
3909	35	SP	3557303	ESTIVA GERBI
3910	41	PR	4100103	ABATIÁ
3911	41	PR	4100202	ADRIANÓPOLIS
3912	41	PR	4100301	AGUDOS DO SUL
3913	41	PR	4100400	ALMIRANTE TAMANDARÉ
3914	41	PR	4100459	ALTAMIRA DO PARANÁ
3915	41	PR	4100509	ALTÔNIA
3916	41	PR	4100608	ALTO PARANÁ
3917	41	PR	4100707	ALTO PIQUIRI
3918	41	PR	4100806	ALVORADA DO SUL
3919	41	PR	4100905	AMAPORÃ
3920	41	PR	4101002	AMPÉRE
3921	41	PR	4101051	ANAHY
3922	41	PR	4101101	ANDIRÁ
3923	41	PR	4101150	ÂNGULO
3924	41	PR	4101200	ANTONINA
3925	41	PR	4101309	ANTÔNIO OLINTO
3926	41	PR	4101408	APUCARANA
3927	41	PR	4101507	ARAPONGAS
3928	41	PR	4101606	ARAPOTI
3929	41	PR	4101655	ARAPUÃ
3930	41	PR	4101705	ARARUNA
3931	41	PR	4101804	ARAUCÁRIA
3932	41	PR	4101853	ARIRANHA DO IVAÍ
3933	41	PR	4101903	ASSAÍ
3934	41	PR	4102000	ASSIS CHATEAUBRIAND
3935	41	PR	4102109	ASTORGA
3936	41	PR	4102208	ATALAIA
3937	41	PR	4102307	BALSA NOVA
3938	41	PR	4102406	BANDEIRANTES
3939	41	PR	4102505	BARBOSA FERRAZ
3940	41	PR	4102604	BARRACÃO
3941	41	PR	4102703	BARRA DO JACARÉ
3942	41	PR	4102752	BELA VISTA DA CAROBA
3943	41	PR	4102802	BELA VISTA DO PARAÍSO
3944	41	PR	4102901	BITURUNA
3945	41	PR	4103008	BOA ESPERANÇA
3946	41	PR	4103024	BOA ESPERANÇA DO IGUAÇU
3947	41	PR	4103040	BOA VENTURA DE SÃO ROQUE
3948	41	PR	4103057	BOA VISTA DA APARECIDA
3949	41	PR	4103107	BOCAIÚVA DO SUL
3950	41	PR	4103156	BOM JESUS DO SUL
3951	41	PR	4103206	BOM SUCESSO
3952	41	PR	4103222	BOM SUCESSO DO SUL
3953	41	PR	4103305	BORRAZÓPOLIS
3954	41	PR	4103354	BRAGANEY
3955	41	PR	4103370	BRASILÂNDIA DO SUL
3956	41	PR	4103404	CAFEARA
3957	41	PR	4103453	CAFELÂNDIA
3958	41	PR	4103479	CAFEZAL DO SUL
3959	41	PR	4103503	CALIFÓRNIA
3960	41	PR	4103602	CAMBARÁ
3961	41	PR	4103701	CAMBÉ
3962	41	PR	4103800	CAMBIRA
3963	41	PR	4103909	CAMPINA DA LAGOA
3964	41	PR	4103958	CAMPINA DO SIMÃO
3965	41	PR	4104006	CAMPINA GRANDE DO SUL
3966	41	PR	4104055	CAMPO BONITO
3967	41	PR	4104105	CAMPO DO TENENTE
3968	41	PR	4104204	CAMPO LARGO
3969	41	PR	4104253	CAMPO MAGRO
3970	41	PR	4104303	CAMPO MOURÃO
3971	41	PR	4104402	CÂNDIDO DE ABREU
3972	41	PR	4104428	CANDÓI
3973	41	PR	4104451	CANTAGALO
3974	41	PR	4104501	CAPANEMA
3975	41	PR	4104600	CAPITÃO LEÔNIDAS MARQUES
3976	41	PR	4104659	CARAMBEÍ
3977	41	PR	4104709	CARLÓPOLIS
3978	41	PR	4104808	CASCAVEL
3979	41	PR	4104907	CASTRO
3980	41	PR	4105003	CATANDUVAS
3981	41	PR	4105102	CENTENÁRIO DO SUL
3982	41	PR	4105201	CERRO AZUL
3983	41	PR	4105300	CÉU AZUL
3984	41	PR	4105409	CHOPINZINHO
3985	41	PR	4105508	CIANORTE
3986	41	PR	4105607	CIDADE GAÚCHA
3987	41	PR	4105706	CLEVELÂNDIA
3988	41	PR	4105805	COLOMBO
3989	41	PR	4105904	COLORADO
3990	41	PR	4106001	CONGONHINHAS
3991	41	PR	4106100	CONSELHEIRO MAIRINCK
3992	41	PR	4106209	CONTENDA
3993	41	PR	4106308	CORBÉLIA
3994	41	PR	4106407	CORNÉLIO PROCÓPIO
3995	41	PR	4106456	CORONEL DOMINGOS SOARES
3996	41	PR	4106506	CORONEL VIVIDA
3997	41	PR	4106555	CORUMBATAÍ DO SUL
3998	41	PR	4106571	CRUZEIRO DO IGUAÇU
3999	41	PR	4106605	CRUZEIRO DO OESTE
4000	41	PR	4106704	CRUZEIRO DO SUL
4001	41	PR	4106803	CRUZ MACHADO
4002	41	PR	4106852	CRUZMALTINA
4003	41	PR	4106902	CURITIBA
4004	41	PR	4107009	CURIÚVA
4005	41	PR	4107108	DIAMANTE DO NORTE
4006	41	PR	4107124	DIAMANTE DO SUL
4007	41	PR	4107157	DIAMANTE D OESTE
4008	41	PR	4107207	DOIS VIZINHOS
4009	41	PR	4107256	DOURADINA
4010	41	PR	4107306	DOUTOR CAMARGO
4011	41	PR	4107405	ENÉAS MARQUES
4012	41	PR	4107504	ENGENHEIRO BELTRÃO
4013	41	PR	4107520	ESPERANÇA NOVA
4014	41	PR	4107538	ENTRE RIOS DO OESTE
4015	41	PR	4107546	ESPIGÃO ALTO DO IGUAÇU
4016	41	PR	4107553	FAROL
4017	41	PR	4107603	FAXINAL
4018	41	PR	4107652	FAZENDA RIO GRANDE
4019	41	PR	4107702	FÊNIX
4020	41	PR	4107736	FERNANDES PINHEIRO
4021	41	PR	4107751	FIGUEIRA
4022	41	PR	4107801	FLORAÍ
4023	41	PR	4107850	FLOR DA SERRA DO SUL
4024	41	PR	4107900	FLORESTA
4025	41	PR	4108007	FLORESTÓPOLIS
4026	41	PR	4108106	FLÓRIDA
4027	41	PR	4108205	FORMOSA DO OESTE
4028	41	PR	4108304	FOZ DO IGUAÇU
4029	41	PR	4108320	FRANCISCO ALVES
4030	41	PR	4108403	FRANCISCO BELTRÃO
4031	41	PR	4108452	FOZ DO JORDÃO
4032	41	PR	4108502	GENERAL CARNEIRO
4033	41	PR	4108551	GODOY MOREIRA
4034	41	PR	4108601	GOIOERÊ
4035	41	PR	4108650	GOIOXIM
4036	41	PR	4108700	GRANDES RIOS
4037	41	PR	4108809	GUAÍRA
4038	41	PR	4108908	GUAIRAÇÁ
4039	41	PR	4108957	GUAMIRANGA
4040	41	PR	4109005	GUAPIRAMA
4041	41	PR	4109104	GUAPOREMA
4042	41	PR	4109203	GUARACI
4043	41	PR	4109302	GUARANIAÇU
4044	41	PR	4109401	GUARAPUAVA
4045	41	PR	4109500	GUARAQUEÇABA
4046	41	PR	4109609	GUARATUBA
4047	41	PR	4109658	HONÓRIO SERPA
4048	41	PR	4109708	IBAITI
4049	41	PR	4109757	IBEMA
4050	41	PR	4109807	IBIPORÃ
4051	41	PR	4109906	ICARAÍMA
4052	41	PR	4110003	IGUARAÇU
4053	41	PR	4110052	IGUATU
4054	41	PR	4110078	IMBAÚ
4055	41	PR	4110102	IMBITUVA
4056	41	PR	4110201	INÁCIO MARTINS
4057	41	PR	4110300	INAJÁ
4058	41	PR	4110409	INDIANÓPOLIS
4059	41	PR	4110508	IPIRANGA
4060	41	PR	4110607	IPORÃ
4061	41	PR	4110656	IRACEMA DO OESTE
4062	41	PR	4110706	IRATI
4063	41	PR	4110805	IRETAMA
4064	41	PR	4110904	ITAGUAJÉ
4065	41	PR	4110953	ITAIPULÂNDIA
4066	41	PR	4111001	ITAMBARACÁ
4067	41	PR	4111100	ITAMBÉ
4068	41	PR	4111209	ITAPEJARA D OESTE
4069	41	PR	4111258	ITAPERUÇU
4070	41	PR	4111308	ITAÚNA DO SUL
4071	41	PR	4111407	IVAÍ
4072	41	PR	4111506	IVAIPORÃ
4073	41	PR	4111555	IVATÉ
4074	41	PR	4111605	IVATUBA
4075	41	PR	4111704	JABOTI
4076	41	PR	4111803	JACAREZINHO
4077	41	PR	4111902	JAGUAPITÃ
4078	41	PR	4112009	JAGUARIAÍVA
4079	41	PR	4112108	JANDAIA DO SUL
4080	41	PR	4112207	JANIÓPOLIS
4081	41	PR	4112306	JAPIRA
4082	41	PR	4112405	JAPURÁ
4083	41	PR	4112504	JARDIM ALEGRE
4084	41	PR	4112603	JARDIM OLINDA
4085	41	PR	4112702	JATAIZINHO
4086	41	PR	4112751	JESUÍTAS
4087	41	PR	4112801	JOAQUIM TÁVORA
4088	41	PR	4112900	JUNDIAÍ DO SUL
4089	41	PR	4112959	JURANDA
4090	41	PR	4113007	JUSSARA
4091	41	PR	4113106	KALORÉ
4092	41	PR	4113205	LAPA
4093	41	PR	4113254	LARANJAL
4094	41	PR	4113304	LARANJEIRAS DO SUL
4095	41	PR	4113403	LEÓPOLIS
4096	41	PR	4113429	LIDIANÓPOLIS
4097	41	PR	4113452	LINDOESTE
4098	41	PR	4113502	LOANDA
4099	41	PR	4113601	LOBATO
4100	41	PR	4113700	LONDRINA
4101	41	PR	4113734	LUIZIANA
4102	41	PR	4113759	LUNARDELLI
4103	41	PR	4113809	LUPIONÓPOLIS
4104	41	PR	4113908	MALLET
4105	41	PR	4114005	MAMBORÊ
4106	41	PR	4114104	MANDAGUAÇU
4107	41	PR	4114203	MANDAGUARI
4108	41	PR	4114302	MANDIRITUBA
4109	41	PR	4114351	MANFRINÓPOLIS
4110	41	PR	4114401	MANGUEIRINHA
4111	41	PR	4114500	MANOEL RIBAS
4112	41	PR	4114609	MARECHAL CÂNDIDO RONDON
4113	41	PR	4114708	MARIA HELENA
4114	41	PR	4114807	MARIALVA
4115	41	PR	4114906	MARILÂNDIA DO SUL
4116	41	PR	4115002	MARILENA
4117	41	PR	4115101	MARILUZ
4118	41	PR	4115200	MARINGÁ
4119	41	PR	4115309	MARIÓPOLIS
4120	41	PR	4115358	MARIPÁ
4121	41	PR	4115408	MARMELEIRO
4122	41	PR	4115457	MARQUINHO
4123	41	PR	4115507	MARUMBI
4124	41	PR	4115606	MATELÂNDIA
4125	41	PR	4115705	MATINHOS
4126	41	PR	4115739	MATO RICO
4127	41	PR	4115754	MAUÁ DA SERRA
4128	41	PR	4115804	MEDIANEIRA
4129	41	PR	4115853	MERCEDES
4130	41	PR	4115903	MIRADOR
4131	41	PR	4116000	MIRASELVA
4132	41	PR	4116059	MISSAL
4133	41	PR	4116109	MOREIRA SALES
4134	41	PR	4116208	MORRETES
4135	41	PR	4116307	MUNHOZ DE MELO
4136	41	PR	4116406	NOSSA SENHORA DAS GRAÇAS
4137	41	PR	4116505	NOVA ALIANÇA DO IVAÍ
4138	41	PR	4116604	NOVA AMÉRICA DA COLINA
4139	41	PR	4116703	NOVA AURORA
4140	41	PR	4116802	NOVA CANTU
4141	41	PR	4116901	NOVA ESPERANÇA
4142	41	PR	4116950	NOVA ESPERANÇA DO SUDOESTE
4143	41	PR	4117008	NOVA FÁTIMA
4144	41	PR	4117057	NOVA LARANJEIRAS
4145	41	PR	4117107	NOVA LONDRINA
4146	41	PR	4117206	NOVA OLÍMPIA
4147	41	PR	4117214	NOVA SANTA BÁRBARA
4148	41	PR	4117222	NOVA SANTA ROSA
4149	41	PR	4117255	NOVA PRATA DO IGUAÇU
4150	41	PR	4117271	NOVA TEBAS
4151	41	PR	4117297	NOVO ITACOLOMI
4152	41	PR	4117305	ORTIGUEIRA
4153	41	PR	4117404	OURIZONA
4154	41	PR	4117453	OURO VERDE DO OESTE
4155	41	PR	4117503	PAIÇANDU
4156	41	PR	4117602	PALMAS
4157	41	PR	4117701	PALMEIRA
4158	41	PR	4117800	PALMITAL
4159	41	PR	4117909	PALOTINA
4160	41	PR	4118006	PARAÍSO DO NORTE
4161	41	PR	4118105	PARANACITY
4162	41	PR	4118204	PARANAGUÁ
4163	41	PR	4118303	PARANAPOEMA
4164	41	PR	4118402	PARANAVAÍ
4165	41	PR	4118451	PATO BRAGADO
4166	41	PR	4118501	PATO BRANCO
4167	41	PR	4118600	PAULA FREITAS
4168	41	PR	4118709	PAULO FRONTIN
4169	41	PR	4118808	PEABIRU
4170	41	PR	4118857	PEROBAL
4171	41	PR	4118907	PÉROLA
4172	41	PR	4119004	PÉROLA D OESTE
4173	41	PR	4119103	PIÊN
4174	41	PR	4119152	PINHAIS
4175	41	PR	4119202	PINHALÃO
4176	41	PR	4119251	PINHAL DE SÃO BENTO
4177	41	PR	4119301	PINHÃO
4178	41	PR	4119400	PIRAÍ DO SUL
4179	41	PR	4119509	PIRAQUARA
4180	41	PR	4119608	PITANGA
4181	41	PR	4119657	PITANGUEIRAS
4182	41	PR	4119707	PLANALTINA DO PARANÁ
4183	41	PR	4119806	PLANALTO
4184	41	PR	4119905	PONTA GROSSA
4185	41	PR	4119954	PONTAL DO PARANÁ
4186	41	PR	4120002	PORECATU
4187	41	PR	4120101	PORTO AMAZONAS
4188	41	PR	4120150	PORTO BARREIRO
4189	41	PR	4120200	PORTO RICO
4190	41	PR	4120309	PORTO VITÓRIA
4191	41	PR	4120333	PRADO FERREIRA
4192	41	PR	4120358	PRANCHITA
4193	41	PR	4120408	PRESIDENTE CASTELO BRANCO
4194	41	PR	4120507	PRIMEIRO DE MAIO
4195	41	PR	4120606	PRUDENTÓPOLIS
4196	41	PR	4120655	QUARTO CENTENÁRIO
4197	41	PR	4120705	QUATIGUÁ
4198	41	PR	4120804	QUATRO BARRAS
4199	41	PR	4120853	QUATRO PONTES
4200	41	PR	4120903	QUEDAS DO IGUAÇU
4201	41	PR	4121000	QUERÊNCIA DO NORTE
4202	41	PR	4121109	QUINTA DO SOL
4203	41	PR	4121208	QUITANDINHA
4204	41	PR	4121257	RAMILÂNDIA
4205	41	PR	4121307	RANCHO ALEGRE
4206	41	PR	4121356	RANCHO ALEGRE D OESTE
4207	41	PR	4121406	REALEZA
4208	41	PR	4121505	REBOUÇAS
4209	41	PR	4121604	RENASCENÇA
4210	41	PR	4121703	RESERVA
4211	41	PR	4121752	RESERVA DO IGUAÇU
4212	41	PR	4121802	RIBEIRÃO CLARO
4213	41	PR	4121901	RIBEIRÃO DO PINHAL
4214	41	PR	4122008	RIO AZUL
4215	41	PR	4122107	RIO BOM
4216	41	PR	4122156	RIO BONITO DO IGUAÇU
4217	41	PR	4122172	RIO BRANCO DO IVAÍ
4218	41	PR	4122206	RIO BRANCO DO SUL
4219	41	PR	4122305	RIO NEGRO
4220	41	PR	4122404	ROLÂNDIA
4221	41	PR	4122503	RONCADOR
4222	41	PR	4122602	RONDON
4223	41	PR	4122651	ROSÁRIO DO IVAÍ
4224	41	PR	4122701	SABÁUDIA
4225	41	PR	4122800	SALGADO FILHO
4226	41	PR	4122909	SALTO DO ITARARÉ
4227	41	PR	4123006	SALTO DO LONTRA
4228	41	PR	4123105	SANTA AMÉLIA
4229	41	PR	4123204	SANTA CECÍLIA DO PAVÃO
4230	41	PR	4123303	SANTA CRUZ DE MONTE CASTELO
4231	41	PR	4123402	SANTA FÉ
4232	41	PR	4123501	SANTA HELENA
4233	41	PR	4123600	SANTA INÊS
4234	41	PR	4123709	SANTA ISABEL DO IVAÍ
4235	41	PR	4123808	SANTA IZABEL DO OESTE
4236	41	PR	4123824	SANTA LÚCIA
4237	41	PR	4123857	SANTA MARIA DO OESTE
4238	41	PR	4123907	SANTA MARIANA
4239	41	PR	4123956	SANTA MÔNICA
4240	41	PR	4124004	SANTANA DO ITARARÉ
4241	41	PR	4124020	SANTA TEREZA DO OESTE
4242	41	PR	4124053	SANTA TEREZINHA DE ITAIPU
4243	41	PR	4124103	SANTO ANTÔNIO DA PLATINA
4244	41	PR	4124202	SANTO ANTÔNIO DO CAIUÁ
4245	41	PR	4124301	SANTO ANTÔNIO DO PARAÍSO
4246	41	PR	4124400	SANTO ANTÔNIO DO SUDOESTE
4247	41	PR	4124509	SANTO INÁCIO
4248	41	PR	4124608	SÃO CARLOS DO IVAÍ
4249	41	PR	4124707	SÃO JERÔNIMO DA SERRA
4250	41	PR	4124806	SÃO JOÃO
4251	41	PR	4124905	SÃO JOÃO DO CAIUÁ
4252	41	PR	4125001	SÃO JOÃO DO IVAÍ
4253	41	PR	4125100	SÃO JOÃO DO TRIUNFO
4254	41	PR	4125209	SÃO JORGE D OESTE
4255	41	PR	4125308	SÃO JORGE DO IVAÍ
4256	41	PR	4125357	SÃO JORGE DO PATROCÍNIO
4257	41	PR	4125407	SÃO JOSÉ DA BOA VISTA
4258	41	PR	4125456	SÃO JOSÉ DAS PALMEIRAS
4259	41	PR	4125506	SÃO JOSÉ DOS PINHAIS
4260	41	PR	4125555	SÃO MANOEL DO PARANÁ
4261	41	PR	4125605	SÃO MATEUS DO SUL
4262	41	PR	4125704	SÃO MIGUEL DO IGUAÇU
4263	41	PR	4125753	SÃO PEDRO DO IGUAÇU
4264	41	PR	4125803	SÃO PEDRO DO IVAÍ
4265	41	PR	4125902	SÃO PEDRO DO PARANÁ
4266	41	PR	4126009	SÃO SEBASTIÃO DA AMOREIRA
4267	41	PR	4126108	SÃO TOMÉ
4268	41	PR	4126207	SAPOPEMA
4269	41	PR	4126256	SARANDI
4270	41	PR	4126272	SAUDADE DO IGUAÇU
4271	41	PR	4126306	SENGÉS
4272	41	PR	4126355	SERRANÓPOLIS DO IGUAÇU
4273	41	PR	4126405	SERTANEJA
4274	41	PR	4126504	SERTANÓPOLIS
4275	41	PR	4126603	SIQUEIRA CAMPOS
4276	41	PR	4126652	SULINA
4277	41	PR	4126678	TAMARANA
4278	41	PR	4126702	TAMBOARA
4279	41	PR	4126801	TAPEJARA
4280	41	PR	4126900	TAPIRA
4281	41	PR	4127007	TEIXEIRA SOARES
4282	41	PR	4127106	TELÊMACO BORBA
4283	41	PR	4127205	TERRA BOA
4284	41	PR	4127304	TERRA RICA
4285	41	PR	4127403	TERRA ROXA
4286	41	PR	4127502	TIBAGI
4287	41	PR	4127601	TIJUCAS DO SUL
4288	41	PR	4127700	TOLEDO
4289	41	PR	4127809	TOMAZINA
4290	41	PR	4127858	TRÊS BARRAS DO PARANÁ
4291	41	PR	4127882	TUNAS DO PARANÁ
4292	41	PR	4127908	TUNEIRAS DO OESTE
4293	41	PR	4127957	TUPÃSSI
4294	41	PR	4127965	TURVO
4295	41	PR	4128005	UBIRATÃ
4296	41	PR	4128104	UMUARAMA
4297	41	PR	4128203	UNIÃO DA VITÓRIA
4298	41	PR	4128302	UNIFLOR
4299	41	PR	4128401	URAÍ
4300	41	PR	4128500	WENCESLAU BRAZ
4301	41	PR	4128534	VENTANIA
4302	41	PR	4128559	VERA CRUZ DO OESTE
4303	41	PR	4128609	VERÊ
4304	41	PR	4128625	VILA ALTA
4305	41	PR	4128633	DOUTOR ULYSSES
4306	41	PR	4128658	VIRMOND
4307	41	PR	4128708	VITORINO
4308	41	PR	4128807	XAMBRÊ
4309	42	SC	4200051	ABDON BATISTA
4310	42	SC	4200101	ABELARDO LUZ
4311	42	SC	4200200	AGROLÂNDIA
4312	42	SC	4200309	AGRONÔMICA
4313	42	SC	4200408	ÁGUA DOCE
4314	42	SC	4200507	ÁGUAS DE CHAPECÓ
4315	42	SC	4200556	ÁGUAS FRIAS
4316	42	SC	4200606	ÁGUAS MORNAS
4317	42	SC	4200705	ALFREDO WAGNER
4318	42	SC	4200754	ALTO BELA VISTA
4319	42	SC	4200804	ANCHIETA
4320	42	SC	4200903	ANGELINA
4321	42	SC	4201000	ANITA GARIBALDI
4322	42	SC	4201109	ANITÁPOLIS
4323	42	SC	4201208	ANTÔNIO CARLOS
4324	42	SC	4201257	APIÚNA
4325	42	SC	4201273	ARABUTÃ
4326	42	SC	4201307	ARAQUARI
4327	42	SC	4201406	ARARANGUÁ
4328	42	SC	4201505	ARMAZÉM
4329	42	SC	4201604	ARROIO TRINTA
4330	42	SC	4201653	ARVOREDO
4331	42	SC	4201703	ASCURRA
4332	42	SC	4201802	ATALANTA
4333	42	SC	4201901	AURORA
4334	42	SC	4201950	BALNEÁRIO ARROIO DO SILVA
4335	42	SC	4202008	BALNEÁRIO CAMBORIÚ
4336	42	SC	4202057	BALNEÁRIO BARRA DO SUL
4337	42	SC	4202073	BALNEÁRIO GAIVOTA
4338	42	SC	4202081	BANDEIRANTE
4339	42	SC	4202099	BARRA BONITA
4340	42	SC	4202107	BARRA VELHA
4341	42	SC	4202131	BELA VISTA DO TOLDO
4342	42	SC	4202156	BELMONTE
4343	42	SC	4202206	BENEDITO NOVO
4344	42	SC	4202305	BIGUAÇU
4345	42	SC	4202404	BLUMENAU
4346	42	SC	4202438	BOCAINA DO SUL
4347	42	SC	4202453	BOMBINHAS
4348	42	SC	4202503	BOM JARDIM DA SERRA
4349	42	SC	4202537	BOM JESUS
4350	42	SC	4202578	BOM JESUS DO OESTE
4351	42	SC	4202602	BOM RETIRO
4352	42	SC	4202701	BOTUVERÁ
4353	42	SC	4202800	BRAÇO DO NORTE
4354	42	SC	4202859	BRAÇO DO TROMBUDO
4355	42	SC	4202875	BRUNÓPOLIS
4356	42	SC	4202909	BRUSQUE
4357	42	SC	4203006	CAÇADOR
4358	42	SC	4203105	CAIBI
4359	42	SC	4203154	CALMON
4360	42	SC	4203204	CAMBORIÚ
4361	42	SC	4203253	CAPÃO ALTO
4362	42	SC	4203303	CAMPO ALEGRE
4363	42	SC	4203402	CAMPO BELO DO SUL
4364	42	SC	4203501	CAMPO ERÊ
4365	42	SC	4203600	CAMPOS NOVOS
4366	42	SC	4203709	CANELINHA
4367	42	SC	4203808	CANOINHAS
4368	42	SC	4203907	CAPINZAL
4369	42	SC	4203956	CAPIVARI DE BAIXO
4370	42	SC	4204004	CATANDUVAS
4371	42	SC	4204103	CAXAMBU DO SUL
4372	42	SC	4204152	CELSO RAMOS
4373	42	SC	4204178	CERRO NEGRO
4374	42	SC	4204194	CHAPADÃO DO LAGEADO
4375	42	SC	4204202	CHAPECÓ
4376	42	SC	4204251	COCAL DO SUL
4377	42	SC	4204301	CONCÓRDIA
4378	42	SC	4204350	CORDILHEIRA ALTA
4379	42	SC	4204400	CORONEL FREITAS
4380	42	SC	4204459	CORONEL MARTINS
4381	42	SC	4204509	CORUPÁ
4382	42	SC	4204558	CORREIA PINTO
4383	42	SC	4204608	CRICIÚMA
4384	42	SC	4204707	CUNHA PORÃ
4385	42	SC	4204756	CUNHATAÍ
4386	42	SC	4204806	CURITIBANOS
4387	42	SC	4204905	DESCANSO
4388	42	SC	4205001	DIONÍSIO CERQUEIRA
4389	42	SC	4205100	DONA EMMA
4390	42	SC	4205159	DOUTOR PEDRINHO
4391	42	SC	4205175	ENTRE RIOS
4392	42	SC	4205191	ERMO
4393	42	SC	4205209	ERVAL VELHO
4394	42	SC	4205308	FAXINAL DOS GUEDES
4395	42	SC	4205357	FLOR DO SERTÃO
4396	42	SC	4205407	FLORIANÓPOLIS
4397	42	SC	4205431	FORMOSA DO SUL
4398	42	SC	4205456	FORQUILHINHA
4399	42	SC	4205506	FRAIBURGO
4400	42	SC	4205555	FREI ROGÉRIO
4401	42	SC	4205605	GALVÃO
4402	42	SC	4205704	GAROPABA
4403	42	SC	4205803	GARUVA
4404	42	SC	4205902	GASPAR
4405	42	SC	4206009	GOVERNADOR CELSO RAMOS
4406	42	SC	4206108	GRÃO PARÁ
4407	42	SC	4206207	GRAVATAL
4408	42	SC	4206306	GUABIRUBA
4409	42	SC	4206405	GUARACIABA
4410	42	SC	4206504	GUARAMIRIM
4411	42	SC	4206603	GUARUJÁ DO SUL
4412	42	SC	4206652	GUATAMBÚ
4413	42	SC	4206702	HERVAL D OESTE
4414	42	SC	4206751	IBIAM
4415	42	SC	4206801	IBICARÉ
4416	42	SC	4206900	IBIRAMA
4417	42	SC	4207007	IÇARA
4418	42	SC	4207106	ILHOTA
4419	42	SC	4207205	IMARUÍ
4420	42	SC	4207304	IMBITUBA
4421	42	SC	4207403	IMBUIA
4422	42	SC	4207502	INDAIAL
4423	42	SC	4207577	IOMERÊ
4424	42	SC	4207601	IPIRA
4425	42	SC	4207650	IPORÃ DO OESTE
4426	42	SC	4207684	IPUAÇU
4427	42	SC	4207700	IPUMIRIM
4428	42	SC	4207759	IRACEMINHA
4429	42	SC	4207809	IRANI
4430	42	SC	4207858	IRATI
4431	42	SC	4207908	IRINEÓPOLIS
4432	42	SC	4208005	ITÁ
4433	42	SC	4208104	ITAIÓPOLIS
4434	42	SC	4208203	ITAJAÍ
4435	42	SC	4208302	ITAPEMA
4436	42	SC	4208401	ITAPIRANGA
4437	42	SC	4208450	ITAPOÁ
4438	42	SC	4208500	ITUPORANGA
4439	42	SC	4208609	JABORÁ
4440	42	SC	4208708	JACINTO MACHADO
4441	42	SC	4208807	JAGUARUNA
4442	42	SC	4208906	JARAGUÁ DO SUL
4443	42	SC	4208955	JARDINÓPOLIS
4444	42	SC	4209003	JOAÇABA
4445	42	SC	4209102	JOINVILLE
4446	42	SC	4209151	JOSÉ BOITEUX
4447	42	SC	4209177	JUPIÁ
4448	42	SC	4209201	LACERDÓPOLIS
4449	42	SC	4209300	LAGES
4450	42	SC	4209409	LAGUNA
4451	42	SC	4209458	LAJEADO GRANDE
4452	42	SC	4209508	LAURENTINO
4453	42	SC	4209607	LAURO MULLER
4454	42	SC	4209706	LEBON RÉGIS
4455	42	SC	4209805	LEOBERTO LEAL
4456	42	SC	4209854	LINDÓIA DO SUL
4457	42	SC	4209904	LONTRAS
4458	42	SC	4210001	LUIZ ALVES
4459	42	SC	4210035	LUZERNA
4460	42	SC	4210050	MACIEIRA
4461	42	SC	4210100	MAFRA
4462	42	SC	4210209	MAJOR GERCINO
4463	42	SC	4210308	MAJOR VIEIRA
4464	42	SC	4210407	MARACAJÁ
4465	42	SC	4210506	MARAVILHA
4466	42	SC	4210555	MAREMA
4467	42	SC	4210605	MASSARANDUBA
4468	42	SC	4210704	MATOS COSTA
4469	42	SC	4210803	MELEIRO
4470	42	SC	4210852	MIRIM DOCE
4471	42	SC	4210902	MODELO
4472	42	SC	4211009	MONDAÍ
4473	42	SC	4211058	MONTE CARLO
4474	42	SC	4211108	MONTE CASTELO
4475	42	SC	4211207	MORRO DA FUMAÇA
4476	42	SC	4211256	MORRO GRANDE
4477	42	SC	4211306	NAVEGANTES
4478	42	SC	4211405	NOVA ERECHIM
4479	42	SC	4211454	NOVA ITABERABA
4480	42	SC	4211504	NOVA TRENTO
4481	42	SC	4211603	NOVA VENEZA
4482	42	SC	4211652	NOVO HORIZONTE
4483	42	SC	4211702	ORLEANS
4484	42	SC	4211751	OTACÍLIO COSTA
4485	42	SC	4211801	OURO
4486	42	SC	4211850	OURO VERDE
4487	42	SC	4211876	PAIAL
4488	42	SC	4211892	PAINEL
4489	42	SC	4211900	PALHOÇA
4490	42	SC	4212007	PALMA SOLA
4491	42	SC	4212056	PALMEIRA
4492	42	SC	4212106	PALMITOS
4493	42	SC	4212205	PAPANDUVA
4494	42	SC	4212239	PARAÍSO
4495	42	SC	4212254	PASSO DE TORRES
4496	42	SC	4212270	PASSOS MAIA
4497	42	SC	4212304	PAULO LOPES
4498	42	SC	4212403	PEDRAS GRANDES
4499	42	SC	4212502	PENHA
4500	42	SC	4212601	PERITIBA
4501	42	SC	4212700	PETROLÂNDIA
4502	42	SC	4212809	PIÇARRAS
4503	42	SC	4212908	PINHALZINHO
4504	42	SC	4213005	PINHEIRO PRETO
4505	42	SC	4213104	PIRATUBA
4506	42	SC	4213153	PLANALTO ALEGRE
4507	42	SC	4213203	POMERODE
4508	42	SC	4213302	PONTE ALTA
4509	42	SC	4213351	PONTE ALTA DO NORTE
4510	42	SC	4213401	PONTE SERRADA
4511	42	SC	4213500	PORTO BELO
4512	42	SC	4213609	PORTO UNIÃO
4513	42	SC	4213708	POUSO REDONDO
4514	42	SC	4213807	PRAIA GRANDE
4515	42	SC	4213906	PRESIDENTE CASTELO BRANCO
4516	42	SC	4214003	PRESIDENTE GETÚLIO
4517	42	SC	4214102	PRESIDENTE NEREU
4518	42	SC	4214151	PRINCESA
4519	42	SC	4214201	QUILOMBO
4520	42	SC	4214300	RANCHO QUEIMADO
4521	42	SC	4214409	RIO DAS ANTAS
4522	42	SC	4214508	RIO DO CAMPO
4523	42	SC	4214607	RIO DO OESTE
4524	42	SC	4214706	RIO DOS CEDROS
4525	42	SC	4214805	RIO DO SUL
4526	42	SC	4214904	RIO FORTUNA
4527	42	SC	4215000	RIO NEGRINHO
4528	42	SC	4215059	RIO RUFINO
4529	42	SC	4215075	RIQUEZA
4530	42	SC	4215109	RODEIO
4531	42	SC	4215208	ROMELÂNDIA
4532	42	SC	4215307	SALETE
4533	42	SC	4215356	SALTINHO
4534	42	SC	4215406	SALTO VELOSO
4535	42	SC	4215455	SANGÃO
4536	42	SC	4215505	SANTA CECÍLIA
4537	42	SC	4215554	SANTA HELENA
4538	42	SC	4215604	SANTA ROSA DE LIMA
4539	42	SC	4215653	SANTA ROSA DO SUL
4540	42	SC	4215679	SANTA TEREZINHA
4541	42	SC	4215687	SANTA TEREZINHA DO PROGRESSO
4542	42	SC	4215695	SANTIAGO DO SUL
4543	42	SC	4215703	SANTO AMARO DA IMPERATRIZ
4544	42	SC	4215752	SÃO BERNARDINO
4545	42	SC	4215802	SÃO BENTO DO SUL
4546	42	SC	4215901	SÃO BONIFÁCIO
4547	42	SC	4216008	SÃO CARLOS
4548	42	SC	4216057	SÃO CRISTOVÃO DO SUL
4549	42	SC	4216107	SÃO DOMINGOS
4550	42	SC	4216206	SÃO FRANCISCO DO SUL
4551	42	SC	4216255	SÃO JOÃO DO OESTE
4552	42	SC	4216305	SÃO JOÃO BATISTA
4553	42	SC	4216354	SÃO JOÃO DO ITAPERIÚ
4554	42	SC	4216404	SÃO JOÃO DO SUL
4555	42	SC	4216503	SÃO JOAQUIM
4556	42	SC	4216602	SÃO JOSÉ
4557	42	SC	4216701	SÃO JOSÉ DO CEDRO
4558	42	SC	4216800	SÃO JOSÉ DO CERRITO
4559	42	SC	4216909	SÃO LOURENÇO DO OESTE
4560	42	SC	4217006	SÃO LUDGERO
4561	42	SC	4217105	SÃO MARTINHO
4562	42	SC	4217154	SÃO MIGUEL DA BOA VISTA
4563	42	SC	4217204	SÃO MIGUEL DO OESTE
4564	42	SC	4217253	SÃO PEDRO DE ALCÂNTARA
4565	42	SC	4217303	SAUDADES
4566	42	SC	4217402	SCHROEDER
4567	42	SC	4217501	SEARA
4568	42	SC	4217550	SERRA ALTA
4569	42	SC	4217600	SIDERÓPOLIS
4570	42	SC	4217709	SOMBRIO
4571	42	SC	4217758	SUL BRASIL
4572	42	SC	4217808	TAIÓ
4573	42	SC	4217907	TANGARÁ
4574	42	SC	4217956	TIGRINHOS
4575	42	SC	4218004	TIJUCAS
4576	42	SC	4218103	TIMBÉ DO SUL
4577	42	SC	4218202	TIMBÓ
4578	42	SC	4218251	TIMBÓ GRANDE
4579	42	SC	4218301	TRÊS BARRAS
4580	42	SC	4218350	TREVISO
4581	42	SC	4218400	TREZE DE MAIO
4582	42	SC	4218509	TREZE TÍLIAS
4583	42	SC	4218608	TROMBUDO CENTRAL
4584	42	SC	4218707	TUBARÃO
4585	42	SC	4218756	TUNÁPOLIS
4586	42	SC	4218806	TURVO
4587	42	SC	4218855	UNIÃO DO OESTE
4588	42	SC	4218905	URUBICI
4589	42	SC	4218954	URUPEMA
4590	42	SC	4219002	URUSSANGA
4591	42	SC	4219101	VARGEÃO
4592	42	SC	4219150	VARGEM
4593	42	SC	4219176	VARGEM BONITA
4594	42	SC	4219200	VIDAL RAMOS
4595	42	SC	4219309	VIDEIRA
4596	42	SC	4219358	VITOR MEIRELES
4597	42	SC	4219408	WITMARSUM
4598	42	SC	4219507	XANXERÊ
4599	42	SC	4219606	XAVANTINA
4600	42	SC	4219705	XAXIM
4601	42	SC	4219853	ZORTÉA
4602	43	RS	4300034	ACEGUÁ
4603	43	RS	4300059	ÁGUA SANTA
4604	43	RS	4300109	AGUDO
4605	43	RS	4300208	AJURICABA
4606	43	RS	4300307	ALECRIM
4607	43	RS	4300406	ALEGRETE
4608	43	RS	4300455	ALEGRIA
4609	43	RS	4300471	ALMIRANTE TAMANDARÉ DO SUL
4610	43	RS	4300505	ALPESTRE
4611	43	RS	4300554	ALTO ALEGRE
4612	43	RS	4300570	ALTO FELIZ
4613	43	RS	4300604	ALVORADA
4614	43	RS	4300638	AMARAL FERRADOR
4615	43	RS	4300646	AMETISTA DO SUL
4616	43	RS	4300661	ANDRÉ DA ROCHA
4617	43	RS	4300703	ANTA GORDA
4618	43	RS	4300802	ANTÔNIO PRADO
4619	43	RS	4300851	ARAMBARÉ
4620	43	RS	4300877	ARARICÁ
4621	43	RS	4300901	ARATIBA
4622	43	RS	4301008	ARROIO DO MEIO
4623	43	RS	4301057	ARROIO DO SAL
4624	43	RS	4301073	ARROIO DO PADRE
4625	43	RS	4301107	ARROIO DOS RATOS
4626	43	RS	4301206	ARROIO DO TIGRE
4627	43	RS	4301305	ARROIO GRANDE
4628	43	RS	4301404	ARVOREZINHA
4629	43	RS	4301503	AUGUSTO PESTANA
4630	43	RS	4301552	ÁUREA
4631	43	RS	4301602	BAGÉ
4632	43	RS	4301636	BALNEÁRIO PINHAL
4633	43	RS	4301651	BARÃO
4634	43	RS	4301701	BARÃO DE COTEGIPE
4635	43	RS	4301750	BARÃO DO TRIUNFO
4636	43	RS	4301800	BARRACÃO
4637	43	RS	4301859	BARRA DO GUARITA
4638	43	RS	4301875	BARRA DO QUARAÍ
4639	43	RS	4301909	BARRA DO RIBEIRO
4640	43	RS	4301925	BARRA DO RIO AZUL
4641	43	RS	4301958	BARRA FUNDA
4642	43	RS	4302006	BARROS CASSAL
4643	43	RS	4302055	BENJAMIN CONSTANT DO SUL
4644	43	RS	4302105	BENTO GONÇALVES
4645	43	RS	4302154	BOA VISTA DAS MISSÕES
4646	43	RS	4302204	BOA VISTA DO BURICÁ
4647	43	RS	4302220	BOA VISTA DO CADEADO
4648	43	RS	4302238	BOA VISTA DO INCRA
4649	43	RS	4302253	BOA VISTA DO SUL
4650	43	RS	4302303	BOM JESUS
4651	43	RS	4302352	BOM PRINCÍPIO
4652	43	RS	4302378	BOM PROGRESSO
4653	43	RS	4302402	BOM RETIRO DO SUL
4654	43	RS	4302451	BOQUEIRÃO DO LEÃO
4655	43	RS	4302501	BOSSOROCA
4656	43	RS	4302584	BOZANO
4657	43	RS	4302600	BRAGA
4658	43	RS	4302659	BROCHIER
4659	43	RS	4302709	BUTIÁ
4660	43	RS	4302808	CAÇAPAVA DO SUL
4661	43	RS	4302907	CACEQUI
4662	43	RS	4303004	CACHOEIRA DO SUL
4663	43	RS	4303103	CACHOEIRINHA
4664	43	RS	4303202	CACIQUE DOBLE
4665	43	RS	4303301	CAIBATÉ
4666	43	RS	4303400	CAIÇARA
4667	43	RS	4303509	CAMAQUÃ
4668	43	RS	4303558	CAMARGO
4669	43	RS	4303608	CAMBARÁ DO SUL
4670	43	RS	4303673	CAMPESTRE DA SERRA
4671	43	RS	4303707	CAMPINA DAS MISSÕES
4672	43	RS	4303806	CAMPINAS DO SUL
4673	43	RS	4303905	CAMPO BOM
4674	43	RS	4304002	CAMPO NOVO
4675	43	RS	4304101	CAMPOS BORGES
4676	43	RS	4304200	CANDELÁRIA
4677	43	RS	4304309	CÂNDIDO GODÓI
4678	43	RS	4304358	CANDIOTA
4679	43	RS	4304408	CANELA
4680	43	RS	4304507	CANGUÇU
4681	43	RS	4304606	CANOAS
4682	43	RS	4304614	CANUDOS DO VALE
4683	43	RS	4304622	CAPÃO BONITO DO SUL
4684	43	RS	4304630	CAPÃO DA CANOA
4685	43	RS	4304655	CAPÃO DO CIPÓ
4686	43	RS	4304663	CAPÃO DO LEÃO
4687	43	RS	4304671	CAPIVARI DO SUL
4688	43	RS	4304689	CAPELA DE SANTANA
4689	43	RS	4304697	CAPITÃO
4690	43	RS	4304705	CARAZINHO
4691	43	RS	4304713	CARAÁ
4692	43	RS	4304804	CARLOS BARBOSA
4693	43	RS	4304853	CARLOS GOMES
4694	43	RS	4304903	CASCA
4695	43	RS	4304952	CASEIROS
4696	43	RS	4305009	CATUÍPE
4697	43	RS	4305108	CAXIAS DO SUL
4698	43	RS	4305116	CENTENÁRIO
4699	43	RS	4305124	CERRITO
4700	43	RS	4305132	CERRO BRANCO
4701	43	RS	4305157	CERRO GRANDE
4702	43	RS	4305173	CERRO GRANDE DO SUL
4703	43	RS	4305207	CERRO LARGO
4704	43	RS	4305306	CHAPADA
4705	43	RS	4305355	CHARQUEADAS
4706	43	RS	4305371	CHARRUA
4707	43	RS	4305405	CHIAPETTA
4708	43	RS	4305439	CHUÍ
4709	43	RS	4305447	CHUVISCA
4710	43	RS	4305454	CIDREIRA
4711	43	RS	4305504	CIRÍACO
4712	43	RS	4305587	COLINAS
4713	43	RS	4305603	COLORADO
4714	43	RS	4305702	CONDOR
4715	43	RS	4305801	CONSTANTINA
4716	43	RS	4305835	COQUEIRO BAIXO
4717	43	RS	4305850	COQUEIROS DO SUL
4718	43	RS	4305871	CORONEL BARROS
4719	43	RS	4305900	CORONEL BICACO
4720	43	RS	4305934	CORONEL PILAR
4721	43	RS	4305959	COTIPORÃ
4722	43	RS	4305975	COXILHA
4723	43	RS	4306007	CRISSIUMAL
4724	43	RS	4306056	CRISTAL
4725	43	RS	4306072	CRISTAL DO SUL
4726	43	RS	4306106	CRUZ ALTA
4727	43	RS	4306130	CRUZALTENSE
4728	43	RS	4306205	CRUZEIRO DO SUL
4729	43	RS	4306304	DAVID CANABARRO
4730	43	RS	4306320	DERRUBADAS
4731	43	RS	4306353	DEZESSEIS DE NOVEMBRO
4732	43	RS	4306379	DILERMANDO DE AGUIAR
4733	43	RS	4306403	DOIS IRMÃOS
4734	43	RS	4306429	DOIS IRMÃOS DAS MISSÕES
4735	43	RS	4306452	DOIS LAJEADOS
4736	43	RS	4306502	DOM FELICIANO
4737	43	RS	4306551	DOM PEDRO DE ALCÂNTARA
4738	43	RS	4306601	DOM PEDRITO
4739	43	RS	4306700	DONA FRANCISCA
4740	43	RS	4306734	DOUTOR MAURÍCIO CARDOSO
4741	43	RS	4306759	DOUTOR RICARDO
4742	43	RS	4306767	ELDORADO DO SUL
4743	43	RS	4306809	ENCANTADO
4744	43	RS	4306908	ENCRUZILHADA DO SUL
4745	43	RS	4306924	ENGENHO VELHO
4746	43	RS	4306932	ENTRE-IJUÍS
4747	43	RS	4306957	ENTRE RIOS DO SUL
4748	43	RS	4306973	EREBANGO
4749	43	RS	4307005	ERECHIM
4750	43	RS	4307054	ERNESTINA
4751	43	RS	4307104	HERVAL
4752	43	RS	4307203	ERVAL GRANDE
4753	43	RS	4307302	ERVAL SECO
4754	43	RS	4307401	ESMERALDA
4755	43	RS	4307450	ESPERANÇA DO SUL
4756	43	RS	4307500	ESPUMOSO
4757	43	RS	4307559	ESTAÇÃO
4758	43	RS	4307609	ESTÂNCIA VELHA
4759	43	RS	4307708	ESTEIO
4760	43	RS	4307807	ESTRELA
4761	43	RS	4307815	ESTRELA VELHA
4762	43	RS	4307831	EUGÊNIO DE CASTRO
4763	43	RS	4307864	FAGUNDES VARELA
4764	43	RS	4307906	FARROUPILHA
4765	43	RS	4308003	FAXINAL DO SOTURNO
4766	43	RS	4308052	FAXINALZINHO
4767	43	RS	4308078	FAZENDA VILANOVA
4768	43	RS	4308102	FELIZ
4769	43	RS	4308201	FLORES DA CUNHA
4770	43	RS	4308250	FLORIANO PEIXOTO
4771	43	RS	4308300	FONTOURA XAVIER
4772	43	RS	4308409	FORMIGUEIRO
4773	43	RS	4308433	FORQUETINHA
4774	43	RS	4308458	FORTALEZA DOS VALOS
4775	43	RS	4308508	FREDERICO WESTPHALEN
4776	43	RS	4308607	GARIBALDI
4777	43	RS	4308656	GARRUCHOS
4778	43	RS	4308706	GAURAMA
4779	43	RS	4308805	GENERAL CÂMARA
4780	43	RS	4308854	GENTIL
4781	43	RS	4308904	GETÚLIO VARGAS
4782	43	RS	4309001	GIRUÁ
4783	43	RS	4309050	GLORINHA
4784	43	RS	4309100	GRAMADO
4785	43	RS	4309126	GRAMADO DOS LOUREIROS
4786	43	RS	4309159	GRAMADO XAVIER
4787	43	RS	4309209	GRAVATAÍ
4788	43	RS	4309258	GUABIJU
4789	43	RS	4309308	GUAÍBA
4790	43	RS	4309407	GUAPORÉ
4791	43	RS	4309506	GUARANI DAS MISSÕES
4792	43	RS	4309555	HARMONIA
4793	43	RS	4309571	HERVEIRAS
4794	43	RS	4309605	HORIZONTINA
4795	43	RS	4309654	HULHA NEGRA
4796	43	RS	4309704	HUMAITÁ
4797	43	RS	4309753	IBARAMA
4798	43	RS	4309803	IBIAÇÁ
4799	43	RS	4309902	IBIRAIARAS
4800	43	RS	4309951	IBIRAPUITÃ
4801	43	RS	4310009	IBIRUBÁ
4802	43	RS	4310108	IGREJINHA
4803	43	RS	4310207	IJUÍ
4804	43	RS	4310306	ILÓPOLIS
4805	43	RS	4310330	IMBÉ
4806	43	RS	4310363	IMIGRANTE
4807	43	RS	4310405	INDEPENDÊNCIA
4808	43	RS	4310413	INHACORÁ
4809	43	RS	4310439	IPÊ
4810	43	RS	4310462	IPIRANGA DO SUL
4811	43	RS	4310504	IRAÍ
4812	43	RS	4310538	ITAARA
4813	43	RS	4310553	ITACURUBI
4814	43	RS	4310579	ITAPUCA
4815	43	RS	4310603	ITAQUI
4816	43	RS	4310652	ITATI
4817	43	RS	4310702	ITATIBA DO SUL
4818	43	RS	4310751	IVORÁ
4819	43	RS	4310801	IVOTI
4820	43	RS	4310850	JABOTICABA
4821	43	RS	4310876	JACUIZINHO
4822	43	RS	4310900	JACUTINGA
4823	43	RS	4311007	JAGUARÃO
4824	43	RS	4311106	JAGUARI
4825	43	RS	4311122	JAQUIRANA
4826	43	RS	4311130	JARI
4827	43	RS	4311155	JÓIA
4828	43	RS	4311205	JÚLIO DE CASTILHOS
4829	43	RS	4311239	LAGOA BONITA DO SUL
4830	43	RS	4311254	LAGOÃO
4831	43	RS	4311270	LAGOA DOS TRÊS CANTOS
4832	43	RS	4311304	LAGOA VERMELHA
4833	43	RS	4311403	LAJEADO
4834	43	RS	4311429	LAJEADO DO BUGRE
4835	43	RS	4311502	LAVRAS DO SUL
4836	43	RS	4311601	LIBERATO SALZANO
4837	43	RS	4311627	LINDOLFO COLLOR
4838	43	RS	4311643	LINHA NOVA
4839	43	RS	4311700	MACHADINHO
4840	43	RS	4311718	MAÇAMBARA
4841	43	RS	4311734	MAMPITUBA
4842	43	RS	4311759	MANOEL VIANA
4843	43	RS	4311775	MAQUINÉ
4844	43	RS	4311791	MARATÁ
4845	43	RS	4311809	MARAU
4846	43	RS	4311908	MARCELINO RAMOS
4847	43	RS	4311981	MARIANA PIMENTEL
4848	43	RS	4312005	MARIANO MORO
4849	43	RS	4312054	MARQUES DE SOUZA
4850	43	RS	4312104	MATA
4851	43	RS	4312138	MATO CASTELHANO
4852	43	RS	4312153	MATO LEITÃO
4853	43	RS	4312179	MATO QUEIMADO
4854	43	RS	4312203	MAXIMILIANO DE ALMEIDA
4855	43	RS	4312252	MINAS DO LEÃO
4856	43	RS	4312302	MIRAGUAÍ
4857	43	RS	4312351	MONTAURI
4858	43	RS	4312377	MONTE ALEGRE DOS CAMPOS
4859	43	RS	4312385	MONTE BELO DO SUL
4860	43	RS	4312401	MONTENEGRO
4861	43	RS	4312427	MORMAÇO
4862	43	RS	4312443	MORRINHOS DO SUL
4863	43	RS	4312450	MORRO REDONDO
4864	43	RS	4312476	MORRO REUTER
4865	43	RS	4312500	MOSTARDAS
4866	43	RS	4312609	MUÇUM
4867	43	RS	4312617	MUITOS CAPÕES
4868	43	RS	4312625	MULITERNO
4869	43	RS	4312658	NÃO-ME-TOQUE
4870	43	RS	4312674	NICOLAU VERGUEIRO
4871	43	RS	4312708	NONOAI
4872	43	RS	4312757	NOVA ALVORADA
4873	43	RS	4312807	NOVA ARAÇÁ
4874	43	RS	4312906	NOVA BASSANO
4875	43	RS	4312955	NOVA BOA VISTA
4876	43	RS	4313003	NOVA BRÉSCIA
4877	43	RS	4313011	NOVA CANDELÁRIA
4878	43	RS	4313037	NOVA ESPERANÇA DO SUL
4879	43	RS	4313060	NOVA HARTZ
4880	43	RS	4313086	NOVA PÁDUA
4881	43	RS	4313102	NOVA PALMA
4882	43	RS	4313201	NOVA PETRÓPOLIS
4883	43	RS	4313300	NOVA PRATA
4884	43	RS	4313334	NOVA RAMADA
4885	43	RS	4313359	NOVA ROMA DO SUL
4886	43	RS	4313375	NOVA SANTA RITA
4887	43	RS	4313391	NOVO CABRAIS
4888	43	RS	4313409	NOVO HAMBURGO
4889	43	RS	4313425	NOVO MACHADO
4890	43	RS	4313441	NOVO TIRADENTES
4891	43	RS	4313466	NOVO XINGU
4892	43	RS	4313490	NOVO BARREIRO
4893	43	RS	4313508	OSÓRIO
4894	43	RS	4313607	PAIM FILHO
4895	43	RS	4313656	PALMARES DO SUL
4896	43	RS	4313706	PALMEIRA DAS MISSÕES
4897	43	RS	4313805	PALMITINHO
4898	43	RS	4313904	PANAMBI
4899	43	RS	4313953	PANTANO GRANDE
4900	43	RS	4314001	PARAÍ
4901	43	RS	4314027	PARAÍSO DO SUL
4902	43	RS	4314035	PARECI NOVO
4903	43	RS	4314050	PAROBÉ
4904	43	RS	4314068	PASSA SETE
4905	43	RS	4314076	PASSO DO SOBRADO
4906	43	RS	4314100	PASSO FUNDO
4907	43	RS	4314134	PAULO BENTO
4908	43	RS	4314159	PAVERAMA
4909	43	RS	4314175	PEDRAS ALTAS
4910	43	RS	4314209	PEDRO OSÓRIO
4911	43	RS	4314308	PEJUÇARA
4912	43	RS	4314407	PELOTAS
4913	43	RS	4314423	PICADA CAFÉ
4914	43	RS	4314456	PINHAL
4915	43	RS	4314464	PINHAL DA SERRA
4916	43	RS	4314472	PINHAL GRANDE
4917	43	RS	4314498	PINHEIRINHO DO VALE
4918	43	RS	4314506	PINHEIRO MACHADO
4919	43	RS	4314555	PIRAPÓ
4920	43	RS	4314605	PIRATINI
4921	43	RS	4314704	PLANALTO
4922	43	RS	4314753	POÇO DAS ANTAS
4923	43	RS	4314779	PONTÃO
4924	43	RS	4314787	PONTE PRETA
4925	43	RS	4314803	PORTÃO
4926	43	RS	4314902	PORTO ALEGRE
4927	43	RS	4315008	PORTO LUCENA
4928	43	RS	4315057	PORTO MAUÁ
4929	43	RS	4315073	PORTO VERA CRUZ
4930	43	RS	4315107	PORTO XAVIER
4931	43	RS	4315131	POUSO NOVO
4932	43	RS	4315149	PRESIDENTE LUCENA
4933	43	RS	4315156	PROGRESSO
4934	43	RS	4315172	PROTÁSIO ALVES
4935	43	RS	4315206	PUTINGA
4936	43	RS	4315305	QUARAÍ
4937	43	RS	4315313	QUATRO IRMÃOS
4938	43	RS	4315321	QUEVEDOS
4939	43	RS	4315354	QUINZE DE NOVEMBRO
4940	43	RS	4315404	REDENTORA
4941	43	RS	4315453	RELVADO
4942	43	RS	4315503	RESTINGA SECA
4943	43	RS	4315552	RIO DOS ÍNDIOS
4944	43	RS	4315602	RIO GRANDE
4945	43	RS	4315701	RIO PARDO
4946	43	RS	4315750	RIOZINHO
4947	43	RS	4315800	ROCA SALES
4948	43	RS	4315909	RODEIO BONITO
4949	43	RS	4315958	ROLADOR
4950	43	RS	4316006	ROLANTE
4951	43	RS	4316105	RONDA ALTA
4952	43	RS	4316204	RONDINHA
4953	43	RS	4316303	ROQUE GONZALES
4954	43	RS	4316402	ROSÁRIO DO SUL
4955	43	RS	4316428	SAGRADA FAMÍLIA
4956	43	RS	4316436	SALDANHA MARINHO
4957	43	RS	4316451	SALTO DO JACUÍ
4958	43	RS	4316477	SALVADOR DAS MISSÕES
4959	43	RS	4316501	SALVADOR DO SUL
4960	43	RS	4316600	SANANDUVA
4961	43	RS	4316709	SANTA BÁRBARA DO SUL
4962	43	RS	4316733	SANTA CECÍLIA DO SUL
4963	43	RS	4316758	SANTA CLARA DO SUL
4964	43	RS	4316808	SANTA CRUZ DO SUL
4965	43	RS	4316907	SANTA MARIA
4966	43	RS	4316956	SANTA MARIA DO HERVAL
4967	43	RS	4316972	SANTA MARGARIDA DO SUL
4968	43	RS	4317004	SANTANA DA BOA VISTA
4969	43	RS	4317103	SANTANA DO LIVRAMENTO
4970	43	RS	4317202	SANTA ROSA
4971	43	RS	4317251	SANTA TEREZA
4972	43	RS	4317301	SANTA VITÓRIA DO PALMAR
4973	43	RS	4317400	SANTIAGO
4974	43	RS	4317509	SANTO ÂNGELO
4975	43	RS	4317558	SANTO ANTÔNIO DO PALMA
4976	43	RS	4317608	SANTO ANTÔNIO DA PATRULHA
4977	43	RS	4317707	SANTO ANTÔNIO DAS MISSÕES
4978	43	RS	4317756	SANTO ANTÔNIO DO PLANALTO
4979	43	RS	4317806	SANTO AUGUSTO
4980	43	RS	4317905	SANTO CRISTO
4981	43	RS	4317954	SANTO EXPEDITO DO SUL
4982	43	RS	4318002	SÃO BORJA
4983	43	RS	4318051	SÃO DOMINGOS DO SUL
4984	43	RS	4318101	SÃO FRANCISCO DE ASSIS
4985	43	RS	4318200	SÃO FRANCISCO DE PAULA
4986	43	RS	4318309	SÃO GABRIEL
4987	43	RS	4318408	SÃO JERÔNIMO
4988	43	RS	4318424	SÃO JOÃO DA URTIGA
4989	43	RS	4318432	SÃO JOÃO DO POLÊSINE
4990	43	RS	4318440	SÃO JORGE
4991	43	RS	4318457	SÃO JOSÉ DAS MISSÕES
4992	43	RS	4318465	SÃO JOSÉ DO HERVAL
4993	43	RS	4318481	SÃO JOSÉ DO HORTÊNCIO
4994	43	RS	4318499	SÃO JOSÉ DO INHACORÁ
4995	43	RS	4318507	SÃO JOSÉ DO NORTE
4996	43	RS	4318606	SÃO JOSÉ DO OURO
4997	43	RS	4318614	SÃO JOSÉ DO SUL
4998	43	RS	4318622	SÃO JOSÉ DOS AUSENTES
4999	43	RS	4318705	SÃO LEOPOLDO
5000	43	RS	4318804	SÃO LOURENÇO DO SUL
5001	43	RS	4318903	SÃO LUIZ GONZAGA
5002	43	RS	4319000	SÃO MARCOS
5003	43	RS	4319109	SÃO MARTINHO
5004	43	RS	4319125	SÃO MARTINHO DA SERRA
5005	43	RS	4319158	SÃO MIGUEL DAS MISSÕES
5006	43	RS	4319208	SÃO NICOLAU
5007	43	RS	4319307	SÃO PAULO DAS MISSÕES
5008	43	RS	4319356	SÃO PEDRO DA SERRA
5009	43	RS	4319364	SÃO PEDRO DAS MISSÕES
5010	43	RS	4319372	SÃO PEDRO DO BUTIÁ
5011	43	RS	4319406	SÃO PEDRO DO SUL
5012	43	RS	4319505	SÃO SEBASTIÃO DO CAÍ
5013	43	RS	4319604	SÃO SEPÉ
5014	43	RS	4319703	SÃO VALENTIM
5015	43	RS	4319711	SÃO VALENTIM DO SUL
5016	43	RS	4319737	SÃO VALÉRIO DO SUL
5017	43	RS	4319752	SÃO VENDELINO
5018	43	RS	4319802	SÃO VICENTE DO SUL
5019	43	RS	4319901	SAPIRANGA
5020	43	RS	4320008	SAPUCAIA DO SUL
5021	43	RS	4320107	SARANDI
5022	43	RS	4320206	SEBERI
5023	43	RS	4320230	SEDE NOVA
5024	43	RS	4320263	SEGREDO
5025	43	RS	4320305	SELBACH
5026	43	RS	4320321	SENADOR SALGADO FILHO
5027	43	RS	4320354	SENTINELA DO SUL
5028	43	RS	4320404	SERAFINA CORRÊA
5029	43	RS	4320453	SÉRIO
5030	43	RS	4320503	SERTÃO
5031	43	RS	4320552	SERTÃO SANTANA
5032	43	RS	4320578	SETE DE SETEMBRO
5033	43	RS	4320602	SEVERIANO DE ALMEIDA
5034	43	RS	4320651	SILVEIRA MARTINS
5035	43	RS	4320677	SINIMBU
5036	43	RS	4320701	SOBRADINHO
5037	43	RS	4320800	SOLEDADE
5038	43	RS	4320859	TABAÍ
5039	43	RS	4320909	TAPEJARA
5040	43	RS	4321006	TAPERA
5041	43	RS	4321105	TAPES
5042	43	RS	4321204	TAQUARA
5043	43	RS	4321303	TAQUARI
5044	43	RS	4321329	TAQUARUÇU DO SUL
5045	43	RS	4321352	TAVARES
5046	43	RS	4321402	TENENTE PORTELA
5047	43	RS	4321436	TERRA DE AREIA
5048	43	RS	4321451	TEUTÔNIA
5049	43	RS	4321469	TIO HUGO
5050	43	RS	4321477	TIRADENTES DO SUL
5051	43	RS	4321493	TOROPI
5052	43	RS	4321501	TORRES
5053	43	RS	4321600	TRAMANDAÍ
5054	43	RS	4321626	TRAVESSEIRO
5055	43	RS	4321634	TRÊS ARROIOS
5056	43	RS	4321667	TRÊS CACHOEIRAS
5057	43	RS	4321709	TRÊS COROAS
5058	43	RS	4321808	TRÊS DE MAIO
5059	43	RS	4321832	TRÊS FORQUILHAS
5060	43	RS	4321857	TRÊS PALMEIRAS
5061	43	RS	4321907	TRÊS PASSOS
5062	43	RS	4321956	TRINDADE DO SUL
5063	43	RS	4322004	TRIUNFO
5064	43	RS	4322103	TUCUNDUVA
5065	43	RS	4322152	TUNAS
5066	43	RS	4322186	TUPANCI DO SUL
5067	43	RS	4322202	TUPANCIRETÃ
5068	43	RS	4322251	TUPANDI
5069	43	RS	4322301	TUPARENDI
5070	43	RS	4322327	TURUÇU
5071	43	RS	4322343	UBIRETAMA
5072	43	RS	4322350	UNIÃO DA SERRA
5073	43	RS	4322376	UNISTALDA
5074	43	RS	4322400	URUGUAIANA
5075	43	RS	4322509	VACARIA
5076	43	RS	4322525	VALE VERDE
5077	43	RS	4322533	VALE DO SOL
5078	43	RS	4322541	VALE REAL
5079	43	RS	4322558	VANINI
5080	43	RS	4322608	VENÂNCIO AIRES
5081	43	RS	4322707	VERA CRUZ
5082	43	RS	4322806	VERANÓPOLIS
5083	43	RS	4322855	VESPASIANO CORREA
5084	43	RS	4322905	VIADUTOS
5085	43	RS	4323002	VIAMÃO
5086	43	RS	4323101	VICENTE DUTRA
5087	43	RS	4323200	VICTOR GRAEFF
5088	43	RS	4323309	VILA FLORES
5089	43	RS	4323358	VILA LÂNGARO
5090	43	RS	4323408	VILA MARIA
5091	43	RS	4323457	VILA NOVA DO SUL
5092	43	RS	4323507	VISTA ALEGRE
5093	43	RS	4323606	VISTA ALEGRE DO PRATA
5094	43	RS	4323705	VISTA GAÚCHA
5095	43	RS	4323754	VITÓRIA DAS MISSÕES
5096	43	RS	4323770	WESTFALIA
5097	43	RS	4323804	XANGRI-LÁ
5098	50	MS	5000203	ÁGUA CLARA
5099	50	MS	5000252	ALCINÓPOLIS
5100	50	MS	5000609	AMAMBAÍ
5101	50	MS	5000708	ANASTÁCIO
5102	50	MS	5000807	ANAURILÂNDIA
5103	50	MS	5000856	ANGÉLICA
5104	50	MS	5000906	ANTÔNIO JOÃO
5105	50	MS	5001003	APARECIDA DO TABOADO
5106	50	MS	5001102	AQUIDAUANA
5107	50	MS	5001243	ARAL MOREIRA
5108	50	MS	5001508	BANDEIRANTES
5109	50	MS	5001904	BATAGUASSU
5110	50	MS	5002001	BATAYPORÃ
5111	50	MS	5002100	BELA VISTA
5112	50	MS	5002159	BODOQUENA
5113	50	MS	5002209	BONITO
5114	50	MS	5002308	BRASILÂNDIA
5115	50	MS	5002407	CAARAPÓ
5116	50	MS	5002605	CAMAPUÃ
5117	50	MS	5002704	CAMPO GRANDE
5118	50	MS	5002803	CARACOL
5119	50	MS	5002902	CASSILÂNDIA
5120	50	MS	5002951	CHAPADÃO DO SUL
5121	50	MS	5003108	CORGUINHO
5122	50	MS	5003157	CORONEL SAPUCAIA
5123	50	MS	5003207	CORUMBÁ
5124	50	MS	5003256	COSTA RICA
5125	50	MS	5003306	COXIM
5126	50	MS	5003454	DEODÁPOLIS
5127	50	MS	5003488	DOIS IRMÃOS DO BURITI
5128	50	MS	5003504	DOURADINA
5129	50	MS	5003702	DOURADOS
5130	50	MS	5003751	ELDORADO
5131	50	MS	5003801	FÁTIMA DO SUL
5132	50	MS	5004007	GLÓRIA DE DOURADOS
5133	50	MS	5004106	GUIA LOPES DA LAGUNA
5134	50	MS	5004304	IGUATEMI
5135	50	MS	5004403	INOCÊNCIA
5136	50	MS	5004502	ITAPORÃ
5137	50	MS	5004601	ITAQUIRAÍ
5138	50	MS	5004700	IVINHEMA
5139	50	MS	5004809	JAPORÃ
5140	50	MS	5004908	JARAGUARI
5141	50	MS	5005004	JARDIM
5142	50	MS	5005103	JATEÍ
5143	50	MS	5005152	JUTI
5144	50	MS	5005202	LADÁRIO
5145	50	MS	5005251	LAGUNA CARAPÃ
5146	50	MS	5005400	MARACAJU
5147	50	MS	5005608	MIRANDA
5148	50	MS	5005681	MUNDO NOVO
5149	50	MS	5005707	NAVIRAÍ
5150	50	MS	5005806	NIOAQUE
5151	50	MS	5006002	NOVA ALVORADA DO SUL
5152	50	MS	5006200	NOVA ANDRADINA
5153	50	MS	5006259	NOVO HORIZONTE DO SUL
5154	50	MS	5006309	PARANAÍBA
5155	50	MS	5006358	PARANHOS
5156	50	MS	5006408	PEDRO GOMES
5157	50	MS	5006606	PONTA PORÃ
5158	50	MS	5006903	PORTO MURTINHO
5159	50	MS	5007109	RIBAS DO RIO PARDO
5160	50	MS	5007208	RIO BRILHANTE
5161	50	MS	5007307	RIO NEGRO
5162	50	MS	5007406	RIO VERDE DE MATO GROSSO
5163	50	MS	5007505	ROCHEDO
5164	50	MS	5007554	SANTA RITA DO PARDO
5165	50	MS	5007695	SÃO GABRIEL DO OESTE
5166	50	MS	5007703	SETE QUEDAS
5167	50	MS	5007802	SELVÍRIA
5168	50	MS	5007901	SIDROLÂNDIA
5169	50	MS	5007935	SONORA
5170	50	MS	5007950	TACURU
5171	50	MS	5007976	TAQUARUSSU
5172	50	MS	5008008	TERENOS
5173	50	MS	5008305	TRÊS LAGOAS
5174	50	MS	5008404	VICENTINA
5175	51	MT	5100102	ACORIZAL
5176	51	MT	5100201	ÁGUA BOA
5177	51	MT	5100250	ALTA FLORESTA
5178	51	MT	5100300	ALTO ARAGUAIA
5179	51	MT	5100359	ALTO BOA VISTA
5180	51	MT	5100409	ALTO GARÇAS
5181	51	MT	5100508	ALTO PARAGUAI
5182	51	MT	5100607	ALTO TAQUARI
5183	51	MT	5100805	APIACÁS
5184	51	MT	5101001	ARAGUAIANA
5185	51	MT	5101209	ARAGUAINHA
5186	51	MT	5101258	ARAPUTANGA
5187	51	MT	5101308	ARENÁPOLIS
5188	51	MT	5101407	ARIPUANÃ
5189	51	MT	5101605	BARÃO DE MELGAÇO
5190	51	MT	5101704	BARRA DO BUGRES
5191	51	MT	5101803	BARRA DO GARÇAS
5192	51	MT	5101852	BOM JESUS DO ARAGUAIA
5193	51	MT	5101902	BRASNORTE
5194	51	MT	5102504	CÁCERES
5195	51	MT	5102603	CAMPINÁPOLIS
5196	51	MT	5102637	CAMPO NOVO DO PARECIS
5197	51	MT	5102678	CAMPO VERDE
5198	51	MT	5102686	CAMPOS DE JÚLIO
5199	51	MT	5102694	CANABRAVA DO NORTE
5200	51	MT	5102702	CANARANA
5201	51	MT	5102793	CARLINDA
5202	51	MT	5102850	CASTANHEIRA
5203	51	MT	5103007	CHAPADA DOS GUIMARÃES
5204	51	MT	5103056	CLÁUDIA
5205	51	MT	5103106	COCALINHO
5206	51	MT	5103205	COLÍDER
5207	51	MT	5103254	COLNIZA
5208	51	MT	5103304	COMODORO
5209	51	MT	5103353	CONFRESA
5210	51	MT	5103361	CONQUISTA D OESTE
5211	51	MT	5103379	COTRIGUAÇU
5212	51	MT	5103403	CUIABÁ
5213	51	MT	5103437	CURVELÂNDIA
5214	51	MT	5103452	DENISE
5215	51	MT	5103502	DIAMANTINO
5216	51	MT	5103601	DOM AQUINO
5217	51	MT	5103700	FELIZ NATAL
5218	51	MT	5103809	FIGUEIRÓPOLIS D OESTE
5219	51	MT	5103858	GAÚCHA DO NORTE
5220	51	MT	5103908	GENERAL CARNEIRO
5221	51	MT	5103957	GLÓRIA D OESTE
5222	51	MT	5104104	GUARANTÃ DO NORTE
5223	51	MT	5104203	GUIRATINGA
5224	51	MT	5104500	INDIAVAÍ
5225	51	MT	5104559	ITAÚBA
5226	51	MT	5104609	ITIQUIRA
5227	51	MT	5104807	JACIARA
5228	51	MT	5104906	JANGADA
5229	51	MT	5105002	JAURU
5230	51	MT	5105101	JUARA
5231	51	MT	5105150	JUÍNA
5232	51	MT	5105176	JURUENA
5233	51	MT	5105200	JUSCIMEIRA
5234	51	MT	5105234	LAMBARI D OESTE
5235	51	MT	5105259	LUCAS DO RIO VERDE
5236	51	MT	5105309	LUCIÁRA
5237	51	MT	5105507	VILA BELA DA SANTÍSSIMA TRINDADE
5238	51	MT	5105580	MARCELÂNDIA
5239	51	MT	5105606	MATUPÁ
5240	51	MT	5105622	MIRASSOL D OESTE
5241	51	MT	5105903	NOBRES
5242	51	MT	5106000	NORTELÂNDIA
5243	51	MT	5106109	NOSSA SENHORA DO LIVRAMENTO
5244	51	MT	5106158	NOVA BANDEIRANTES
5245	51	MT	5106174	NOVA NAZARÉ
5246	51	MT	5106182	NOVA LACERDA
5247	51	MT	5106190	NOVA SANTA HELENA
5248	51	MT	5106208	NOVA BRASILÂNDIA
5249	51	MT	5106216	NOVA CANAÃ DO NORTE
5250	51	MT	5106224	NOVA MUTUM
5251	51	MT	5106232	NOVA OLÍMPIA
5252	51	MT	5106240	NOVA UBIRATÃ
5253	51	MT	5106257	NOVA XAVANTINA
5254	51	MT	5106265	NOVO MUNDO
5255	51	MT	5106273	NOVO HORIZONTE DO NORTE
5256	51	MT	5106281	NOVO SÃO JOAQUIM
5257	51	MT	5106299	PARANAÍTA
5258	51	MT	5106307	PARANATINGA
5259	51	MT	5106315	NOVO SANTO ANTÔNIO
5260	51	MT	5106372	PEDRA PRETA
5261	51	MT	5106422	PEIXOTO DE AZEVEDO
5262	51	MT	5106455	PLANALTO DA SERRA
5263	51	MT	5106505	POCONÉ
5264	51	MT	5106653	PONTAL DO ARAGUAIA
5265	51	MT	5106703	PONTE BRANCA
5266	51	MT	5106752	PONTES E LACERDA
5267	51	MT	5106778	PORTO ALEGRE DO NORTE
5268	51	MT	5106802	PORTO DOS GAÚCHOS
5269	51	MT	5106828	PORTO ESPERIDIÃO
5270	51	MT	5106851	PORTO ESTRELA
5271	51	MT	5107008	POXORÉO
5272	51	MT	5107040	PRIMAVERA DO LESTE
5273	51	MT	5107065	QUERÊNCIA
5274	51	MT	5107107	SÃO JOSÉ DOS QUATRO MARCOS
5275	51	MT	5107156	RESERVA DO CABAÇAL
5276	51	MT	5107180	RIBEIRÃO CASCALHEIRA
5277	51	MT	5107198	RIBEIRÃOZINHO
5278	51	MT	5107206	RIO BRANCO
5279	51	MT	5107248	SANTA CARMEM
5280	51	MT	5107263	SANTO AFONSO
5281	51	MT	5107297	SÃO JOSÉ DO POVO
5282	51	MT	5107305	SÃO JOSÉ DO RIO CLARO
5283	51	MT	5107354	SÃO JOSÉ DO XINGU
5284	51	MT	5107404	SÃO PEDRO DA CIPA
5285	51	MT	5107578	RONDOLÂNDIA
5286	51	MT	5107602	RONDONÓPOLIS
5287	51	MT	5107701	ROSÁRIO OESTE
5288	51	MT	5107743	SANTA CRUZ DO XINGU
5289	51	MT	5107750	SALTO DO CÉU
5290	51	MT	5107768	SANTA RITA DO TRIVELATO
5291	51	MT	5107776	SANTA TEREZINHA
5292	51	MT	5107792	SANTO ANTÔNIO DO LESTE
5293	51	MT	5107800	SANTO ANTÔNIO DO LEVERGER
5294	51	MT	5107859	SÃO FÉLIX DO ARAGUAIA
5295	51	MT	5107875	SAPEZAL
5296	51	MT	5107883	SERRA NOVA DOURADA
5297	51	MT	5107909	SINOP
5298	51	MT	5107925	SORRISO
5299	51	MT	5107941	TABAPORÃ
5300	51	MT	5107958	TANGARÁ DA SERRA
5301	51	MT	5108006	TAPURAH
5302	51	MT	5108055	TERRA NOVA DO NORTE
5303	51	MT	5108105	TESOURO
5304	51	MT	5108204	TORIXORÉU
5305	51	MT	5108303	UNIÃO DO SUL
5306	51	MT	5108352	VALE DE SÃO DOMINGOS
5307	51	MT	5108402	VÁRZEA GRANDE
5308	51	MT	5108501	VERA
5309	51	MT	5108600	VILA RICA
5310	51	MT	5108808	NOVA GUARITA
5311	51	MT	5108857	NOVA MARILÂNDIA
5312	51	MT	5108907	NOVA MARINGÁ
5313	51	MT	5108956	NOVA MONTE VERDE
5314	52	GO	5200050	ABADIA DE GOIÁS
5315	52	GO	5200100	ABADIÂNIA
5316	52	GO	5200134	ACREÚNA
5317	52	GO	5200159	ADELÂNDIA
5318	52	GO	5200175	ÁGUA FRIA DE GOIÁS
5319	52	GO	5200209	ÁGUA LIMPA
5320	52	GO	5200258	ÁGUAS LINDAS DE GOIÁS
5321	52	GO	5200308	ALEXÂNIA
5322	52	GO	5200506	ALOÂNDIA
5323	52	GO	5200555	ALTO HORIZONTE
5324	52	GO	5200605	ALTO PARAÍSO DE GOIÁS
5325	52	GO	5200803	ALVORADA DO NORTE
5326	52	GO	5200829	AMARALINA
5327	52	GO	5200852	AMERICANO DO BRASIL
5328	52	GO	5200902	AMORINÓPOLIS
5329	52	GO	5201108	ANÁPOLIS
5330	52	GO	5201207	ANHANGUERA
5331	52	GO	5201306	ANICUNS
5332	52	GO	5201405	APARECIDA DE GOIÂNIA
5333	52	GO	5201454	APARECIDA DO RIO DOCE
5334	52	GO	5201504	APORÉ
5335	52	GO	5201603	ARAÇU
5336	52	GO	5201702	ARAGARÇAS
5337	52	GO	5201801	ARAGOIÂNIA
5338	52	GO	5202155	ARAGUAPAZ
5339	52	GO	5202353	ARENÓPOLIS
5340	52	GO	5202502	ARUANÃ
5341	52	GO	5202601	AURILÂNDIA
5342	52	GO	5202809	AVELINÓPOLIS
5343	52	GO	5203104	BALIZA
5344	52	GO	5203203	BARRO ALTO
5345	52	GO	5203302	BELA VISTA DE GOIÁS
5346	52	GO	5203401	BOM JARDIM DE GOIÁS
5347	52	GO	5203500	BOM JESUS DE GOIÁS
5348	52	GO	5203559	BONFINÓPOLIS
5349	52	GO	5203575	BONÓPOLIS
5350	52	GO	5203609	BRAZABRANTES
5351	52	GO	5203807	BRITÂNIA
5352	52	GO	5203906	BURITI ALEGRE
5353	52	GO	5203939	BURITI DE GOIÁS
5354	52	GO	5203962	BURITINÓPOLIS
5355	52	GO	5204003	CABECEIRAS
5356	52	GO	5204102	CACHOEIRA ALTA
5357	52	GO	5204201	CACHOEIRA DE GOIÁS
5358	52	GO	5204250	CACHOEIRA DOURADA
5359	52	GO	5204300	CAÇU
5360	52	GO	5204409	CAIAPÔNIA
5361	52	GO	5204508	CALDAS NOVAS
5362	52	GO	5204557	CALDAZINHA
5363	52	GO	5204607	CAMPESTRE DE GOIÁS
5364	52	GO	5204656	CAMPINAÇU
5365	52	GO	5204706	CAMPINORTE
5366	52	GO	5204805	CAMPO ALEGRE DE GOIÁS
5367	52	GO	5204854	CAMPO LIMPO DE GOIÁS
5368	52	GO	5204904	CAMPOS BELOS
5369	52	GO	5204953	CAMPOS VERDES
5370	52	GO	5205000	CARMO DO RIO VERDE
5371	52	GO	5205059	CASTELÂNDIA
5372	52	GO	5205109	CATALÃO
5373	52	GO	5205208	CATURAÍ
5374	52	GO	5205307	CAVALCANTE
5375	52	GO	5205406	CERES
5376	52	GO	5205455	CEZARINA
5377	52	GO	5205471	CHAPADÃO DO CÉU
5378	52	GO	5205497	CIDADE OCIDENTAL
5379	52	GO	5205513	COCALZINHO DE GOIÁS
5380	52	GO	5205521	COLINAS DO SUL
5381	52	GO	5205703	CÓRREGO DO OURO
5382	52	GO	5205802	CORUMBÁ DE GOIÁS
5383	52	GO	5205901	CORUMBAÍBA
5384	52	GO	5206206	CRISTALINA
5385	52	GO	5206305	CRISTIANÓPOLIS
5386	52	GO	5206404	CRIXÁS
5387	52	GO	5206503	CROMÍNIA
5388	52	GO	5206602	CUMARI
5389	52	GO	5206701	DAMIANÓPOLIS
5390	52	GO	5206800	DAMOLÂNDIA
5391	52	GO	5206909	DAVINÓPOLIS
5392	52	GO	5207105	DIORAMA
5393	52	GO	5207253	DOVERLÂNDIA
5394	52	GO	5207352	EDEALINA
5395	52	GO	5207402	EDÉIA
5396	52	GO	5207501	ESTRELA DO NORTE
5397	52	GO	5207535	FAINA
5398	52	GO	5207600	FAZENDA NOVA
5399	52	GO	5207808	FIRMINÓPOLIS
5400	52	GO	5207907	FLORES DE GOIÁS
5401	52	GO	5208004	FORMOSA
5402	52	GO	5208103	FORMOSO
5403	52	GO	5208152	GAMELEIRA DE GOIÁS
5404	52	GO	5208301	DIVINÓPOLIS DE GOIÁS
5405	52	GO	5208400	GOIANÁPOLIS
5406	52	GO	5208509	GOIANDIRA
5407	52	GO	5208608	GOIANÉSIA
5408	52	GO	5208707	GOIÂNIA
5409	52	GO	5208806	GOIANIRA
5410	52	GO	5208905	GOIÁS
5411	52	GO	5209101	GOIATUBA
5412	52	GO	5209150	GOUVELÂNDIA
5413	52	GO	5209200	GUAPÓ
5414	52	GO	5209291	GUARAÍTA
5415	52	GO	5209408	GUARANI DE GOIÁS
5416	52	GO	5209457	GUARINOS
5417	52	GO	5209606	HEITORAÍ
5418	52	GO	5209705	HIDROLÂNDIA
5419	52	GO	5209804	HIDROLINA
5420	52	GO	5209903	IACIARA
5421	52	GO	5209937	INACIOLÂNDIA
5422	52	GO	5209952	INDIARA
5423	52	GO	5210000	INHUMAS
5424	52	GO	5210109	IPAMERI
5425	52	GO	5210158	IPIRANGA DE GOIÁS
5426	52	GO	5210208	IPORÁ
5427	52	GO	5210307	ISRAELÂNDIA
5428	52	GO	5210406	ITABERAÍ
5429	52	GO	5210562	ITAGUARI
5430	52	GO	5210604	ITAGUARU
5431	52	GO	5210802	ITAJÁ
5432	52	GO	5210901	ITAPACI
5433	52	GO	5211008	ITAPIRAPUÃ
5434	52	GO	5211206	ITAPURANGA
5435	52	GO	5211305	ITARUMÃ
5436	52	GO	5211404	ITAUÇU
5437	52	GO	5211503	ITUMBIARA
5438	52	GO	5211602	IVOLÂNDIA
5439	52	GO	5211701	JANDAIA
5440	52	GO	5211800	JARAGUÁ
5441	52	GO	5211909	JATAÍ
5442	52	GO	5212006	JAUPACI
5443	52	GO	5212055	JESÚPOLIS
5444	52	GO	5212105	JOVIÂNIA
5445	52	GO	5212204	JUSSARA
5446	52	GO	5212253	LAGOA SANTA
5447	52	GO	5212303	LEOPOLDO DE BULHÕES
5448	52	GO	5212501	LUZIÂNIA
5449	52	GO	5212600	MAIRIPOTABA
5450	52	GO	5212709	MAMBAÍ
5451	52	GO	5212808	MARA ROSA
5452	52	GO	5212907	MARZAGÃO
5453	52	GO	5212956	MATRINCHÃ
5454	52	GO	5213004	MAURILÂNDIA
5455	52	GO	5213053	MIMOSO DE GOIÁS
5456	52	GO	5213087	MINAÇU
5457	52	GO	5213103	MINEIROS
5458	52	GO	5213400	MOIPORÁ
5459	52	GO	5213509	MONTE ALEGRE DE GOIÁS
5460	52	GO	5213707	MONTES CLAROS DE GOIÁS
5461	52	GO	5213756	MONTIVIDIU
5462	52	GO	5213772	MONTIVIDIU DO NORTE
5463	52	GO	5213806	MORRINHOS
5464	52	GO	5213855	MORRO AGUDO DE GOIÁS
5465	52	GO	5213905	MOSSÂMEDES
5466	52	GO	5214002	MOZARLÂNDIA
5467	52	GO	5214051	MUNDO NOVO
5468	52	GO	5214101	MUTUNÓPOLIS
5469	52	GO	5214408	NAZÁRIO
5470	52	GO	5214507	NERÓPOLIS
5471	52	GO	5214606	NIQUELÂNDIA
5472	52	GO	5214705	NOVA AMÉRICA
5473	52	GO	5214804	NOVA AURORA
5474	52	GO	5214838	NOVA CRIXÁS
5475	52	GO	5214861	NOVA GLÓRIA
5476	52	GO	5214879	NOVA IGUAÇU DE GOIÁS
5477	52	GO	5214903	NOVA ROMA
5478	52	GO	5215009	NOVA VENEZA
5479	52	GO	5215207	NOVO BRASIL
5480	52	GO	5215231	NOVO GAMA
5481	52	GO	5215256	NOVO PLANALTO
5482	52	GO	5215306	ORIZONA
5483	52	GO	5215405	OURO VERDE DE GOIÁS
5484	52	GO	5215504	OUVIDOR
5485	52	GO	5215603	PADRE BERNARDO
5486	52	GO	5215652	PALESTINA DE GOIÁS
5487	52	GO	5215702	PALMEIRAS DE GOIÁS
5488	52	GO	5215801	PALMELO
5489	52	GO	5215900	PALMINÓPOLIS
5490	52	GO	5216007	PANAMÁ
5491	52	GO	5216304	PARANAIGUARA
5492	52	GO	5216403	PARAÚNA
5493	52	GO	5216452	PEROLÂNDIA
5494	52	GO	5216809	PETROLINA DE GOIÁS
5495	52	GO	5216908	PILAR DE GOIÁS
5496	52	GO	5217104	PIRACANJUBA
5497	52	GO	5217203	PIRANHAS
5498	52	GO	5217302	PIRENÓPOLIS
5499	52	GO	5217401	PIRES DO RIO
5500	52	GO	5217609	PLANALTINA
5501	52	GO	5217708	PONTALINA
5502	52	GO	5218003	PORANGATU
5503	52	GO	5218052	PORTEIRÃO
5504	52	GO	5218102	PORTELÂNDIA
5505	52	GO	5218300	POSSE
5506	52	GO	5218391	PROFESSOR JAMIL
5507	52	GO	5218508	QUIRINÓPOLIS
5508	52	GO	5218607	RIALMA
5509	52	GO	5218706	RIANÁPOLIS
5510	52	GO	5218789	RIO QUENTE
5511	52	GO	5218805	RIO VERDE
5512	52	GO	5218904	RUBIATABA
5513	52	GO	5219001	SANCLERLÂNDIA
5514	52	GO	5219100	SANTA BÁRBARA DE GOIÁS
5515	52	GO	5219209	SANTA CRUZ DE GOIÁS
5516	52	GO	5219258	SANTA FÉ DE GOIÁS
5517	52	GO	5219308	SANTA HELENA DE GOIÁS
5518	52	GO	5219357	SANTA ISABEL
5519	52	GO	5219407	SANTA RITA DO ARAGUAIA
5520	52	GO	5219456	SANTA RITA DO NOVO DESTINO
5521	52	GO	5219506	SANTA ROSA DE GOIÁS
5522	52	GO	5219605	SANTA TEREZA DE GOIÁS
5523	52	GO	5219704	SANTA TEREZINHA DE GOIÁS
5524	52	GO	5219712	SANTO ANTÔNIO DA BARRA
5525	52	GO	5219738	SANTO ANTÔNIO DE GOIÁS
5526	52	GO	5219753	SANTO ANTÔNIO DO DESCOBERTO
5527	52	GO	5219803	SÃO DOMINGOS
5528	52	GO	5219902	SÃO FRANCISCO DE GOIÁS
5529	52	GO	5220009	SÃO JOÃO D ALIANÇA
5530	52	GO	5220058	SÃO JOÃO DA PARAÚNA
5531	52	GO	5220108	SÃO LUÍS DE MONTES BELOS
5532	52	GO	5220157	SÃO LUÍZ DO NORTE
5533	52	GO	5220207	SÃO MIGUEL DO ARAGUAIA
5534	52	GO	5220264	SÃO MIGUEL DO PASSA QUATRO
5535	52	GO	5220280	SÃO PATRÍCIO
5536	52	GO	5220405	SÃO SIMÃO
5537	52	GO	5220454	SENADOR CANEDO
5538	52	GO	5220504	SERRANÓPOLIS
5539	52	GO	5220603	SILVÂNIA
5540	52	GO	5220686	SIMOLÂNDIA
5541	52	GO	5220702	SÍTIO D ABADIA
5542	52	GO	5221007	TAQUARAL DE GOIÁS
5543	52	GO	5221080	TERESINA DE GOIÁS
5544	52	GO	5221197	TEREZÓPOLIS DE GOIÁS
5545	52	GO	5221304	TRÊS RANCHOS
5546	52	GO	5221403	TRINDADE
5547	52	GO	5221452	TROMBAS
5548	52	GO	5221502	TURVÂNIA
5549	52	GO	5221551	TURVELÂNDIA
5550	52	GO	5221577	UIRAPURU
5551	52	GO	5221601	URUAÇU
5552	52	GO	5221700	URUANA
5553	52	GO	5221809	URUTAÍ
5554	52	GO	5221858	VALPARAÍSO DE GOIÁS
5555	52	GO	5221908	VARJÃO
5556	52	GO	5222005	VIANÓPOLIS
5557	52	GO	5222054	VICENTINÓPOLIS
5558	52	GO	5222203	VILA BOA
5559	52	GO	5222302	VILA PROPÍCIO
5560	53	DF	5300108	BRASÍLIA
\.
