package com.adme.gestion.sonorisation.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

  private static final Map<String, List<LocalDate>> MAP_ASSIGNATION = new HashMap<>();

  private Utils() {

  }

  public static Map<String, List<LocalDate>> buildWeeklyDate(LocalDate dateCommencement,
      LocalDate dateFin) {

    List<LocalDate> weekList ;
    do {
      weekList=new ArrayList<>();

      var lundi = Year.now().atMonth(dateCommencement.getMonth()).atDay(1)
          .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

      var samedi = Year.now().atMonth(dateCommencement.getMonth()).atDay(1)
          .with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));

      weekList.add(lundi);
      weekList.add(samedi);

      while (lundi.getMonth().equals(dateCommencement.getMonth()) || samedi.getMonth()
          .equals(dateCommencement.getMonth())) {

        lundi = lundi.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        samedi = samedi.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        if (lundi.getMonth().equals(dateCommencement.getMonth())) {
          weekList.add(lundi);
          weekList.add(samedi);
        }
      }

      MAP_ASSIGNATION.put(dateCommencement.getMonth().name(), weekList);

      dateCommencement = dateCommencement.plusMonths(1);
      //

    } while (dateCommencement.getMonth().getValue() <= dateFin.getMonth().getValue());

    return MAP_ASSIGNATION;

  }

}
