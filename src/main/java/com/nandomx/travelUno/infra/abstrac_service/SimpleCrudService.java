package com.nandomx.travelUno.infra.abstrac_service;


public interface SimpleCrudService<RQ,RS,ID>  {
    RS create(RQ request);
    RS read(ID request);

    void delete(ID id);
}
