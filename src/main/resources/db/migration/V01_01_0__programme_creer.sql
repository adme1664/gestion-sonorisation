create table programme
(
    programme_id      uuid                   not null primary key,
    nom_programme     character varying(200) not null,
    type_programme    character varying(50)  not null,
    date_commencement date                   not null,
    date_fin          date                   not null,
    date_creation     timestamp              not null,
    user_create       character varying(10)  not null,
    date_modification timestamp,
    user_modification character varying(10)
);