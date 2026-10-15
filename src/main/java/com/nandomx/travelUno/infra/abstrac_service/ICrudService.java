package com.nandomx.travelUno.infra.abstrac_service;

public interface ICrudService<RQ,RS,ID> {

    RS create(RQ request);
    RS read(ID request);
    RS update(RQ request, ID id);
    void delete(ID id);

}
