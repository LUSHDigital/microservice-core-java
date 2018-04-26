package com.lush.microservice.core.utils;

import com.lush.microservice.core.models.PaginationDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

  /**
   * Method name : setPaginationInfo.
   * Description : Pagination Info setting to PaginationDto.
   *
   * @param page
   * @return
   */
  public static PaginationDto setPaginationInfo(Page page) {
    PaginationDto paginationDto = new PaginationDto();

    long totalCount = page.getTotalElements();
    int perSize = page.getSize();
    int currentPage = page.getPageable().getPageNumber() + 1;
    int lastPages = page.getTotalPages();

    paginationDto.setTotal(totalCount);
    paginationDto.setPer_page(perSize);
    paginationDto.setCurrent_page(currentPage);
    paginationDto.setLast_page(lastPages);

    if (currentPage + 1 <= lastPages) {
      currentPage = currentPage + 1;
    }
    paginationDto.setNext_page(currentPage);

    if (currentPage - 1 >= 0) {
      currentPage = currentPage - 1;
    }
    paginationDto.setPrev_page(currentPage);

    return paginationDto;
  }
}
