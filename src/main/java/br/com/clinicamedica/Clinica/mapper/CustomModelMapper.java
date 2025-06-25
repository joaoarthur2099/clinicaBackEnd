package br.com.clinicamedica.Clinica.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomModelMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static<Origin, Destination> Destination
    parseObject(Origin origin, Class<Destination> destination){
        return mapper.map(origin, destination);
    }

    public static<Origin, Destination> List<Destination>
    parseObjectList(List<Origin> origin, Class<Destination> destination){
        List<Destination> destinationList = new ArrayList<>();
        for(Origin object : origin){
            destinationList.add(mapper.map(object, destination));
        }
        return destinationList;
    }

}
