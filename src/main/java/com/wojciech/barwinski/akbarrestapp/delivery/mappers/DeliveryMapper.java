package com.wojciech.barwinski.akbarrestapp.delivery.mappers;

import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.PhotoSessionsByPhotographDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.PhotoSessionsBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.SessionByPhotographDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.sesionDTOS.SessionBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.FullTradesBySalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.FullTradesBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.TradeBySalesmanDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.dtos.tradeDTOS.TradeBySchoolDTO;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import com.wojciech.barwinski.akbarrestapp.delivery.entities.Trade;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DeliveryMapper {

    public PhotoSessionsBySchoolDTO mapSessionsToSessionsBySchoolDTOS(Set<PhotoSession> sessionsBySchool) {
        School school = sessionsBySchool.stream().findFirst().get().getSchool();
        List<SessionBySchoolDTO> photoSessionDTOS = new ArrayList<>();

        for (PhotoSession photoSession : sessionsBySchool) {
            photoSessionDTOS.add(
                    SessionBySchoolDTO.builder()
                            .photographer(photoSession.getPhotographer().getFirstName() + " " + photoSession.getPhotographer().getLastName())
                            .photographingDate(photoSession.getPhotographingDate())
                            .photographyDaysCount(photoSession.getPhotographyDaysCount())
                            .photographyNote(photoSession.getNote())
                            .build());
        }
        return new PhotoSessionsBySchoolDTO(school.getRspo(),
                school.getName(),
                photoSessionDTOS);
    }

    public PhotoSessionsByPhotographDTO mapSessionsToSessionsByPhotographDTO(Set<PhotoSession> sessionsByPhotograph) {
        Photographer photographer = sessionsByPhotograph.stream().findFirst().get().getPhotographer();
        List<SessionByPhotographDTO> photoSessionDTOS = new ArrayList<>();

        for (PhotoSession photoSession : sessionsByPhotograph) {
            photoSessionDTOS.add(
                    SessionByPhotographDTO.builder()
                            .schoolName(photoSession.getSchool().getName())
                            .photographingDate(photoSession.getPhotographingDate())
                            .photographyDaysCount(photoSession.getPhotographyDaysCount())
                            .photographyNote(photoSession.getNote())
                            .build());
        }
        return new PhotoSessionsByPhotographDTO(photographer.getId(),
                photographer.getFirstName() + " " + photographer.getLastName(),
                photoSessionDTOS);
    }

    public FullTradesBySchoolDTO mapTradesToFullTradeBySchool(Set<Trade> tradesBySchool) {
        School school = tradesBySchool.stream().findFirst().get().getSchool();
        List<TradeBySchoolDTO> tradeBySchoolDTOS = new ArrayList<>();

        for (Trade trade : tradesBySchool) {
            tradeBySchoolDTOS.add(
                    TradeBySchoolDTO.builder()
                            .salesmanName(trade.getSalesman().getFirstName() + " " + trade.getSalesman().getLastName())
                            .signContractDate(trade.getSignContractDate())
                            .note(trade.getNote())
                            .build());
        }

        return new FullTradesBySchoolDTO(school.getRspo(),
                school.getName(),
                tradeBySchoolDTOS);
    }

    public FullTradesBySalesmanDTO mapTradesToFullTradeBySalesman(Set<Trade> tradesBySalesman) {
        Salesman salesman = tradesBySalesman.stream().findFirst().get().getSalesman();
        List<TradeBySalesmanDTO> tradeBySalesmanDTOS = new ArrayList<>();

        for (Trade trade : tradesBySalesman) {
            tradeBySalesmanDTOS.add(
              TradeBySalesmanDTO.builder()
                      .schoolName(trade.getSchool().getName())
                      .signContractDate(trade.getSignContractDate())
                      .note(trade.getNote())
                      .build());
        }

        return new FullTradesBySalesmanDTO(salesman.getId(),
                salesman.getFirstName() + " " + salesman.getLastName(),
                tradeBySalesmanDTOS);
    }
}
