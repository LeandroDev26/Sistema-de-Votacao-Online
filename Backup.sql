--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-11-28 09:05:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 25795)
-- Name: enquetes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enquetes (
    id integer NOT NULL,
    pergunta character varying(255) NOT NULL
);


ALTER TABLE public.enquetes OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25794)
-- Name: enquetes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.enquetes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.enquetes_id_seq OWNER TO postgres;

--
-- TOC entry 4922 (class 0 OID 0)
-- Dependencies: 217
-- Name: enquetes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.enquetes_id_seq OWNED BY public.enquetes.id;


--
-- TOC entry 220 (class 1259 OID 25802)
-- Name: opcoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opcoes (
    id integer NOT NULL,
    id_enquete integer NOT NULL,
    texto_opcao character varying(100) NOT NULL
);


ALTER TABLE public.opcoes OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25801)
-- Name: opcoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.opcoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.opcoes_id_seq OWNER TO postgres;

--
-- TOC entry 4923 (class 0 OID 0)
-- Dependencies: 219
-- Name: opcoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.opcoes_id_seq OWNED BY public.opcoes.id;


--
-- TOC entry 222 (class 1259 OID 25814)
-- Name: votos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.votos (
    id integer NOT NULL,
    usuario character varying(100) NOT NULL,
    id_enquete integer NOT NULL,
    opcao_escolhida character varying(100) NOT NULL,
    data_voto timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.votos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25813)
-- Name: votos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.votos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.votos_id_seq OWNER TO postgres;

--
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 221
-- Name: votos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.votos_id_seq OWNED BY public.votos.id;


--
-- TOC entry 4752 (class 2604 OID 25798)
-- Name: enquetes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enquetes ALTER COLUMN id SET DEFAULT nextval('public.enquetes_id_seq'::regclass);


--
-- TOC entry 4753 (class 2604 OID 25805)
-- Name: opcoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcoes ALTER COLUMN id SET DEFAULT nextval('public.opcoes_id_seq'::regclass);


--
-- TOC entry 4754 (class 2604 OID 25817)
-- Name: votos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.votos ALTER COLUMN id SET DEFAULT nextval('public.votos_id_seq'::regclass);


--
-- TOC entry 4912 (class 0 OID 25795)
-- Dependencies: 218
-- Data for Name: enquetes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.enquetes (id, pergunta) FROM stdin;
1	Qual a melhor linguagem para iniciantes?
2	Qual o melhor anime de todos os tempos?
3	Qual a melhor franquia de filmes?
4	Qual a melhor plataforma para jogar hoje?
\.


--
-- TOC entry 4914 (class 0 OID 25802)
-- Dependencies: 220
-- Data for Name: opcoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.opcoes (id, id_enquete, texto_opcao) FROM stdin;
1	1	Java
2	1	Python
3	1	C
4	1	JavaScript
5	2	Dragon Ball Z
6	2	Naruto
7	2	One Piece
8	2	Cavaleiros do Zodíaco
9	3	Star Wars
10	3	O Senhor dos Anéis
11	3	Harry Potter
12	3	Marvel
13	4	PC
14	4	PlayStation
15	4	Nintendo
16	4	Xbox
\.


--
-- TOC entry 4916 (class 0 OID 25814)
-- Dependencies: 222
-- Data for Name: votos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.votos (id, usuario, id_enquete, opcao_escolhida, data_voto) FROM stdin;
1	Mario	4	Nintendo	2025-11-26 15:50:36.751314
\.


--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 217
-- Name: enquetes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enquetes_id_seq', 4, true);


--
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 219
-- Name: opcoes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcoes_id_seq', 16, true);


--
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 221
-- Name: votos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.votos_id_seq', 1, true);


--
-- TOC entry 4757 (class 2606 OID 25800)
-- Name: enquetes enquetes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enquetes
    ADD CONSTRAINT enquetes_pkey PRIMARY KEY (id);


--
-- TOC entry 4759 (class 2606 OID 25807)
-- Name: opcoes opcoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcoes
    ADD CONSTRAINT opcoes_pkey PRIMARY KEY (id);


--
-- TOC entry 4761 (class 2606 OID 25822)
-- Name: votos unico_voto_por_enquete; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.votos
    ADD CONSTRAINT unico_voto_por_enquete UNIQUE (usuario, id_enquete);


--
-- TOC entry 4763 (class 2606 OID 25820)
-- Name: votos votos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.votos
    ADD CONSTRAINT votos_pkey PRIMARY KEY (id);


--
-- TOC entry 4764 (class 2606 OID 25808)
-- Name: opcoes opcoes_id_enquete_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcoes
    ADD CONSTRAINT opcoes_id_enquete_fkey FOREIGN KEY (id_enquete) REFERENCES public.enquetes(id);


--
-- TOC entry 4765 (class 2606 OID 25823)
-- Name: votos votos_id_enquete_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.votos
    ADD CONSTRAINT votos_id_enquete_fkey FOREIGN KEY (id_enquete) REFERENCES public.enquetes(id);


-- Completed on 2025-11-28 09:05:12

--
-- PostgreSQL database dump complete
--

