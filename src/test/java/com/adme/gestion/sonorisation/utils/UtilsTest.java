package com.adme.gestion.sonorisation.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UtilsTest {
  @Test
  void buildWeeklyDate(){
    //given
    LocalDate dateCommencement = LocalDate.of(2023, Month.APRIL,1);

    LocalDate dateFin = dateCommencement.plusMonths(2);

    var result = Utils.buildWeeklyDate(dateCommencement, dateFin);

    assertEquals(18, result.size());

  }

}
