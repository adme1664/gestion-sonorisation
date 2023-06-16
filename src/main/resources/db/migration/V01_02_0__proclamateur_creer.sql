create table proclamateur
(
    proclamateur_id        uuid                   not null primary key,
    nom_famille            character varying(200) not null,
    prenom                 character varying(200) not null,
    sexe                   character varying(1),
    date_naissance      date                   not null,
    date_bapteme           date,
    addresse               character varying(200) not null,
    telephone              character varying(30),
    email                  character varying(100),
    ancien                 boolean                         default false,
    serviteur_ministeriel  boolean                         default false,
    pionnier_permanent     boolean                         default false,
    assigne                boolean                         default false,
    servir_dans_visio      boolean                         default false,
    servir_dans_console    boolean                         default false,
    servir_dans_microphone boolean                         default true,
    ne_plus_servir         boolean                         default false,
    date_creation          timestamp              not null default current_timestamp,
    user_create            character varying(10)  not null,
    date_modification      timestamp,
    user_modification      character varying(10)
);

comment
on table proclamateur is 'Table qui gere l''ensemble des proclamateurs';
