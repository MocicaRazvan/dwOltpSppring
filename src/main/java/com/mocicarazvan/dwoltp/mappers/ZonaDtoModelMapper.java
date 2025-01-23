//package com.mocicarazvan.dwoltp.mappers;
//
//import com.mocicarazvan.dwoltp.dtos.zona.ZonaBody;
//import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
//import com.mocicarazvan.dwoltp.models.Zona;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ZonaDtoModelMapper implements DtosModelMapper<ZonaBody, Zona, Zona> {
//    @Override
//    public Zona fromBodyToModel(ZonaBody zonaBody) {
//        return Zona.builder()
//                .nume(zonaBody.getNume())
//                .iso(zonaBody.getIso())
//                .build();
//    }
//
//    @Override
//    public Zona fromModelToResponse(Zona zona) {
//        return zona;
//    }
//}
