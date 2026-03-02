package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.TradSimpStandardDto;
import com.liu.trachunom.entity.character.TradSimpStandard;
import com.liu.trachunom.entity.character.TradSimpStandardId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.character.TradSimpStandardService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class TradSimpStandardEndpoint {
    private final EntityMapper entityMapper;
    private final TradSimpStandardService tradSimpStandardService;

    public TradSimpStandardDto save(TradSimpStandardDto tradSimpStandardDto) {
        TradSimpStandard tradSimpStandard = entityMapper.toTradSimpStandard(tradSimpStandardDto);
        tradSimpStandardService.save(tradSimpStandard);
        return tradSimpStandardDto;
    }

    public void delete(TradSimpStandardId tradSimpStandardId) {
        tradSimpStandardService.deleteById(tradSimpStandardId);
    }

}
