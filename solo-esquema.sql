--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8
-- Dumped by pg_dump version 13.1

-- Started on 2022-07-10 16:30:45

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
-- TOC entry 205 (class 1259 OID 28202)
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
-- TOC entry 206 (class 1259 OID 28210)
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
-- TOC entry 202 (class 1259 OID 28196)
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
-- TOC entry 207 (class 1259 OID 28218)
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
-- TOC entry 203 (class 1259 OID 28198)
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
-- TOC entry 204 (class 1259 OID 28200)
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
-- TOC entry 2702 (class 2606 OID 28209)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id_account);


--
-- TOC entry 2704 (class 2606 OID 28217)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id_client);


--
-- TOC entry 2708 (class 2606 OID 28222)
-- Name: movement movement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_pkey PRIMARY KEY (id_movement);


--
-- TOC entry 2706 (class 2606 OID 28224)
-- Name: client uk_powwvjq5dtrded35jufhbmcsd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_powwvjq5dtrded35jufhbmcsd UNIQUE (identification);


--
-- TOC entry 2710 (class 2606 OID 28230)
-- Name: movement fk6uevyj67obsv30kpmn40o0tam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movement
    ADD CONSTRAINT fk6uevyj67obsv30kpmn40o0tam FOREIGN KEY (fk_account) REFERENCES public.account(id_account);


--
-- TOC entry 2709 (class 2606 OID 28225)
-- Name: account fkcq20y12fsb4owak2xeyxt4uqi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkcq20y12fsb4owak2xeyxt4uqi FOREIGN KEY (fk_client) REFERENCES public.client(id_client);


-- Completed on 2022-07-10 16:30:45

--
-- PostgreSQL database dump complete
--

