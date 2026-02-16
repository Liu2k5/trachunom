package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.TradSimpStandardDto;
import com.liu.trachunom.entity.TradSimpStandard;
import com.liu.trachunom.entity.TradSimpStandardId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.TradSimpStandardService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
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
