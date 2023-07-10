package com.adme.gestion.sonorisation.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UtilsTest {
  @Test
  void buildWeeklyDate(){
    //given
    LocalDate dateCommencement = LocalDate.now();
    LocalDate dateFin = dateCommencement.plusMonths(1);

    var result = Utils.buildWeeklyDate(dateCommencement, dateFin);

    assertEquals(2, result.size());

  }

}
