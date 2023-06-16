create table assignation_microphone
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

alter table assignation_microphone
    add constraint assignation_console_programme_fk_4
        foreign key (programme_id) references programme_sonorisation (programme_id);

alter table assignation_microphone
    add constraint assignation_console_proclamateur_fk_5
        foreign key (programme_id) references proclamateur (proclamateur_id);