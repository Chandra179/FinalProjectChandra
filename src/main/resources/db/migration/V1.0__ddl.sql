-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

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
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: tb_agency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_agency (
    id bigint NOT NULL,
    code character varying(255),
    details character varying(255),
    name character varying(255),
    owner_user_id bigint
);


ALTER TABLE public.tb_agency OWNER TO postgres;

--
-- Name: tb_agency_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_agency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_agency_id_seq OWNER TO postgres;

--
-- Name: tb_agency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_agency_id_seq OWNED BY public.tb_agency.id;


--
-- Name: tb_bus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_bus (
    id bigint NOT NULL,
    capacity integer NOT NULL,
    code character varying(255),
    make character varying(255),
    agency_id bigint
);


ALTER TABLE public.tb_bus OWNER TO postgres;

--
-- Name: tb_bus_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_bus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_bus_id_seq OWNER TO postgres;

--
-- Name: tb_bus_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_bus_id_seq OWNED BY public.tb_bus.id;


--
-- Name: tb_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_role (
    id bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.tb_role OWNER TO postgres;

--
-- Name: tb_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_role_id_seq OWNER TO postgres;

--
-- Name: tb_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_role_id_seq OWNED BY public.tb_role.id;


--
-- Name: tb_stop; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_stop (
    id bigint NOT NULL,
    code character varying(255),
    detail character varying(255),
    name character varying(255)
);


ALTER TABLE public.tb_stop OWNER TO postgres;

--
-- Name: tb_stop_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_stop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_stop_id_seq OWNER TO postgres;

--
-- Name: tb_stop_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_stop_id_seq OWNED BY public.tb_stop.id;


--
-- Name: tb_ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_ticket (
    id bigint NOT NULL,
    cancellable boolean,
    journey_date character varying(255),
    seat_number integer NOT NULL,
    user_id bigint,
    trip_schedule_id bigint
);


ALTER TABLE public.tb_ticket OWNER TO postgres;

--
-- Name: tb_ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_ticket_id_seq OWNER TO postgres;

--
-- Name: tb_ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_ticket_id_seq OWNED BY public.tb_ticket.id;


--
-- Name: tb_trip; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_trip (
    id bigint NOT NULL,
    fare integer NOT NULL,
    journey_time integer NOT NULL,
    agency_id bigint,
    bus_id bigint,
    dest_stop_id bigint,
    source_stop_id bigint
);


ALTER TABLE public.tb_trip OWNER TO postgres;

--
-- Name: tb_trip_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_trip_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_trip_id_seq OWNER TO postgres;

--
-- Name: tb_trip_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_trip_id_seq OWNED BY public.tb_trip.id;


--
-- Name: tb_trip_schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_trip_schedule (
    id bigint NOT NULL,
    available_seats integer NOT NULL,
    trip_date character varying(255),
    trip_id bigint
);


ALTER TABLE public.tb_trip_schedule OWNER TO postgres;

--
-- Name: tb_trip_schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_trip_schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_trip_schedule_id_seq OWNER TO postgres;

--
-- Name: tb_trip_schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_trip_schedule_id_seq OWNED BY public.tb_trip_schedule.id;


--
-- Name: tb_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_user (
    id bigint NOT NULL,
    email character varying(50),
    first_name character varying(120),
    last_name character varying(120),
    mobile_number character varying(120),
    password character varying(120),
    username character varying(50)
);


ALTER TABLE public.tb_user OWNER TO postgres;

--
-- Name: tb_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_user_id_seq OWNER TO postgres;

--
-- Name: tb_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_user_id_seq OWNED BY public.tb_user.id;


--
-- Name: tb_user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.tb_user_roles OWNER TO postgres;

--
-- Name: tb_agency id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_agency ALTER COLUMN id SET DEFAULT nextval('public.tb_agency_id_seq'::regclass);


--
-- Name: tb_bus id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_bus ALTER COLUMN id SET DEFAULT nextval('public.tb_bus_id_seq'::regclass);


--
-- Name: tb_role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_role ALTER COLUMN id SET DEFAULT nextval('public.tb_role_id_seq'::regclass);


--
-- Name: tb_stop id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_stop ALTER COLUMN id SET DEFAULT nextval('public.tb_stop_id_seq'::regclass);


--
-- Name: tb_ticket id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_ticket ALTER COLUMN id SET DEFAULT nextval('public.tb_ticket_id_seq'::regclass);


--
-- Name: tb_trip id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip ALTER COLUMN id SET DEFAULT nextval('public.tb_trip_id_seq'::regclass);


--
-- Name: tb_trip_schedule id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip_schedule ALTER COLUMN id SET DEFAULT nextval('public.tb_trip_schedule_id_seq'::regclass);


--
-- Name: tb_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user ALTER COLUMN id SET DEFAULT nextval('public.tb_user_id_seq'::regclass);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: tb_agency tb_agency_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_agency
    ADD CONSTRAINT tb_agency_pkey PRIMARY KEY (id);


--
-- Name: tb_bus tb_bus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_bus
    ADD CONSTRAINT tb_bus_pkey PRIMARY KEY (id);


--
-- Name: tb_role tb_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_role
    ADD CONSTRAINT tb_role_pkey PRIMARY KEY (id);


--
-- Name: tb_stop tb_stop_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_stop
    ADD CONSTRAINT tb_stop_pkey PRIMARY KEY (id);


--
-- Name: tb_ticket tb_ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_ticket
    ADD CONSTRAINT tb_ticket_pkey PRIMARY KEY (id);


--
-- Name: tb_trip tb_trip_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip
    ADD CONSTRAINT tb_trip_pkey PRIMARY KEY (id);


--
-- Name: tb_trip_schedule tb_trip_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip_schedule
    ADD CONSTRAINT tb_trip_schedule_pkey PRIMARY KEY (id);


--
-- Name: tb_user tb_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user
    ADD CONSTRAINT tb_user_pkey PRIMARY KEY (id);


--
-- Name: tb_user_roles tb_user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user_roles
    ADD CONSTRAINT tb_user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: tb_user uk4vih17mube9j7cqyjlfbcrk4m; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user
    ADD CONSTRAINT uk4vih17mube9j7cqyjlfbcrk4m UNIQUE (email);


--
-- Name: tb_user uk4wv83hfajry5tdoamn8wsqa6x; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user
    ADD CONSTRAINT uk4wv83hfajry5tdoamn8wsqa6x UNIQUE (username);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: tb_user_roles fk19t64ocsol5x06fy2cytp7gey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user_roles
    ADD CONSTRAINT fk19t64ocsol5x06fy2cytp7gey FOREIGN KEY (user_id) REFERENCES public.tb_user(id);


--
-- Name: tb_bus fk5u7ho69v708db1wfmwyxy7ada; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_bus
    ADD CONSTRAINT fk5u7ho69v708db1wfmwyxy7ada FOREIGN KEY (agency_id) REFERENCES public.tb_agency(id);


--
-- Name: tb_ticket fk97lwd2e4myiak0iv8liqy6m73; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_ticket
    ADD CONSTRAINT fk97lwd2e4myiak0iv8liqy6m73 FOREIGN KEY (trip_schedule_id) REFERENCES public.tb_trip_schedule(id);


--
-- Name: tb_agency fkaf52n3hv8gkrwutflsx3dsu0a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_agency
    ADD CONSTRAINT fkaf52n3hv8gkrwutflsx3dsu0a FOREIGN KEY (owner_user_id) REFERENCES public.tb_user(id);


--
-- Name: tb_trip fkb9ya2jdoqp7e561bcffkfcg0w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip
    ADD CONSTRAINT fkb9ya2jdoqp7e561bcffkfcg0w FOREIGN KEY (agency_id) REFERENCES public.tb_agency(id);


--
-- Name: tb_user_roles fkft1jmfcluls775jqp5142wvl8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_user_roles
    ADD CONSTRAINT fkft1jmfcluls775jqp5142wvl8 FOREIGN KEY (role_id) REFERENCES public.tb_role(id);


--
-- Name: tb_trip_schedule fkh8m8v7xrx1gxk97wmqcgdwkpd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip_schedule
    ADD CONSTRAINT fkh8m8v7xrx1gxk97wmqcgdwkpd FOREIGN KEY (trip_id) REFERENCES public.tb_trip(id);


--
-- Name: tb_trip fklxk3n3ls1rxdlrt0mly1utso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip
    ADD CONSTRAINT fklxk3n3ls1rxdlrt0mly1utso FOREIGN KEY (dest_stop_id) REFERENCES public.tb_stop(id);


--
-- Name: tb_trip fkmm1rg49eegmxrqb6l0rmds4xf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip
    ADD CONSTRAINT fkmm1rg49eegmxrqb6l0rmds4xf FOREIGN KEY (source_stop_id) REFERENCES public.tb_stop(id);


--
-- Name: tb_trip fkn2846c608hiibwh4rfl73ebtl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_trip
    ADD CONSTRAINT fkn2846c608hiibwh4rfl73ebtl FOREIGN KEY (bus_id) REFERENCES public.tb_bus(id);


--
-- Name: tb_ticket fkoa6dths1eu8gnrlchutafn97o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_ticket
    ADD CONSTRAINT fkoa6dths1eu8gnrlchutafn97o FOREIGN KEY (user_id) REFERENCES public.tb_user(id);


--
-- PostgreSQL database dump complete
--