package com.lush.microservice.core.utils;

import com.lush.microservice.core.enums.ExceptionType;
import com.lush.microservice.core.exceptions.CoreException;
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
   * @throws Exception
   */
  public static PaginationDto setPaginationInfo(Page page) throws Exception {
    PaginationDto paginationDto = new PaginationDto();

    long totalCount = page.getTotalElements();
    int perSize = page.getSize();
    int currentPage = page.getPageable().getPageNumber() + 1;
    int lastPages = page.getTotalPages();

    if (totalCount == 0) {
      currentPage = 0;
    }

    paginationDto.setTotal(totalCount);
    paginationDto.setPer_page(perSize);
    paginationDto.setCurrent_page(currentPage);
    paginationDto.setLast_page(lastPages);

    int nextPage = currentPage;
    if (nextPage < lastPages) {
      nextPage = nextPage + 1;
    }
    paginationDto.setNext_page(nextPage);

    int prevPage = currentPage;
    if (prevPage > 0) {
      prevPage = prevPage - 1;
    }
    paginationDto.setPrev_page(prevPage);

    if (currentPage > lastPages) {
      throw new CoreException().setCommonExceptoin(ExceptionType.NOT_FOUND_PAGENATION);
    }

    return paginationDto;
  }
}
