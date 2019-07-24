package ua.lviv.lgs.service;

import java.util.Map;

import ua.lviv.lgs.domain.Magazine;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface MagazineService extends AbstractCRUD<Magazine>{
	public Map<Integer, Magazine> readAllMap();

}
