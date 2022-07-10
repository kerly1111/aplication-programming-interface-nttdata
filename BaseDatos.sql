--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8
-- Dumped by pg_dump version 12.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: nttdata; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE nttdata WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Ecuador.1252' LC_CTYPE = 'Spanish_Ecuador.1252';


ALTER DATABASE nttdata OWNER TO postgres;

\connect nttdata

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id_account bigint NOT NULL,
    account_number character varying(255) NOT NULL,
    account_type character varying(255) NOT NULL,
    fk_client bigint NOT NULL,
    initial_balance double precision NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id_client bigint NOT NULL,
    address character varying(255) NOT NULL,
    age integer NOT NULL,
    gender character varying(255) NOT NULL,
    identification character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_client_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_client_seq OWNER TO postgres;

--
-- Name: movement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movement (
    id_movement bigint NOT NULL,
    balance double precision NOT NULL,
    date date NOT NULL,
    fk_account bigint NOT NULL,
    movement_type character varying(255) NOT NULL,
    value double precision NOT NULL
);


ALTER TABLE public.movement OWNER TO postgres;

--
-- Name: movement_id_movement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movement_id_movement_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movement_id_movement_seq OWNER TO postgres;

--
-- Name: person_id_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_person_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_person_seq OWNER TO postgres;

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account VALUES (1, '478758', 'Ahorro', 1, 2000, true);
INSERT INTO public.account VALUES (2, '225487', 'Corriente', 2, 100, true);
INSERT INTO public.account VALUES (3, '495878', 'Ahorro', 3, 0, true);
INSERT INTO public.account VALUES (4, '496825', 'Ahorro', 2, 540, true);
INSERT INTO public.account VALUES (5, '585545', 'Corriente', 1, 1000, true);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client VALUES (1, 'Otavalo sn y principal', 20, 'Masculino', '1234567890', 'Jose Lema', '098254785', '1234', true);
INSERT INTO public.client VALUES (2, 'Amazonas y  NNUU', 22, 'Femenino', '1234567891', 'Marianela Montalvo', '097548965', '5678', true);
INSERT INTO public.client VALUES (3, '13 junio y Equinoccial', 28, 'Masculino', '1205871222', 'Juan Osorio', '098874587', '1245', true);


--
-- Data for Name: movement; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movement VALUES (1, 1425, '2022-06-30', 1, 'Retiro', 575);
INSERT INTO public.movement VALUES (2, 700, '2022-02-10', 2, 'Deposito', 600);
INSERT INTO public.movement VALUES (3, 150, '2022-07-02', 3, 'Deposito', 150);
INSERT INTO public.movement VALUES (4, 0, '2022-02-07', 4, 'Retiro', 540);


--
-- Name: client_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_client_seq', 51, true);


--
-- Name: movement_id_movement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movement_id_movement_seq', 51, true);


--
-- Name: person_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_person_seq', 51, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id_account);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id_client);


--
-- Name: movement movement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_pkey PRIMARY KEY (id_movement);


--
-- Name: client uk_powwvjq5dtrded35jufhbmcsd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_powwvjq5dtrded35jufhbmcsd UNIQUE (identification);


--
-- Name: movement fk6uevyj67obsv30kpmn40o0tam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT fk6uevyj67obsv30kpmn40o0tam FOREIGN KEY (fk_account) REFERENCES public.account(id_account);


--
-- Name: account fkcq20y12fsb4owak2xeyxt4uqi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkcq20y12fsb4owak2xeyxt4uqi FOREIGN KEY (fk_client) REFERENCES public.client(id_client);


--
-- PostgreSQL database dump complete
--

