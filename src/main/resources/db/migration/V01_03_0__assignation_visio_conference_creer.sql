create table assignation_visio_conference
(
    assignation_id    uuid                  not null primary key,
    programme_id      uuid                  not null,
    proclamateur_id   uuid                  not null,
    date_assignation  timestamp             not null,
    date_creation     timestamp             not null default current_timestamp,
    user_create       character varying(10) not null default 'system',
    date_modification timestamp,
    user_modification character varying(10)
);

alter table assignation_visio_conference
    add constraint assignation_visio_conference_programme_fk
        foreign key (programme_id) references programme_sonorisation (programme_id);

alter table assignation_visio_conference
    add constraint visio_conference_proclamteur_fk foreign key (proclamateur_id)
        references proclamateur (proclamateur_id);