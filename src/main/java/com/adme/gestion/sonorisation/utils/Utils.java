package com.adme.gestion.sonorisation.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

  private static final Map<String, List<LocalDate>> MAP_ASSIGNATION = new HashMap<>();

  private Utils() {

  }

  public static List<LocalDate> buildWeeklyDate(LocalDate dateCommencement,
      LocalDate dateFin) {

    return dateCommencement.datesUntil(dateFin).filter(
        dateAssignation -> dateAssignation.getDayOfWeek().equals(DayOfWeek.MONDAY)
            || dateAssignation.getDayOfWeek().equals(DayOfWeek.SATURDAY)).toList();
//    do {
//      weekList = new ArrayList<>();
//
//      var lundi = Year.now().atMonth(dateCommencement.getMonth()).atDay(1)
//          .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//
//      var samedi = Year.now().atMonth(dateCommencement.getMonth()).atDay(1)
//          .with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
//
//      weekList.add(lundi);
//      weekList.add(samedi);
//
//      while (lundi.getMonth().equals(dateCommencement.getMonth()) || samedi.getMonth()
//          .equals(dateCommencement.getMonth())) {
//
//        lundi = lundi.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
//        samedi = samedi.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
//
//        weekList.add(lundi);
//        weekList.add(samedi);
//
//      }
//
//      MAP_ASSIGNATION.put(dateCommencement.getMonth().name(), weekList);
//
//      dateCommencement = dateCommencement.plusMonths(1);
//      //
//
//    } while (dateCommencement.getMonth().getValue() <= dateFin.getMonth().getValue());
//    MAP_ASSIGNATION.put("")
//
//    return MAP_ASSIGNATION;

  }

}
