create table assignation
(
    assignation_id    uuid                  not null primary key,
    programme_id      uuid                  not null,
    proclamateur_id   uuid                  not null,
    type_assignation  character varying(25) not null,
    date_assignation  date             not null,
    date_creation     timestamp             not null default current_timestamp,
    user_create       character varying(10) not null default 'system',
    date_modification timestamp,
    user_modification character varying(10)
);

alter table assignation
    add constraint assignation_programme_fk
        foreign key (programme_id) references programme (programme_id);

alter table assignation
    add constraint assignation_proclamateur_fk foreign key (proclamateur_id)
        references proclamateur (proclamateur_id);