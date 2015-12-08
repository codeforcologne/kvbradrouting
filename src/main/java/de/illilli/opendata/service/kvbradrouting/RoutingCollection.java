package de.illilli.opendata.service.kvbradrouting;

import java.util.ArrayList;
import java.util.List;

import org.postgis.Geometry;
import org.postgis.LineString;

import de.illilli.opendata.service.kvbradrouting.jdbc.RoutingDao;
import de.illilli.opendata.service.kvbradrouting.jdbc.Select;

public class RoutingCollection {

	private List<RoutingBo> routingList;

	public RoutingCollection(Select<RoutingDao> select) {
		routingList = new ArrayList<RoutingBo>();

		List<RoutingDao> routingList = select.getDbObjectList();
		for (RoutingDao dao : routingList) {
			RoutingBo bo = new RoutingBo();
			bo.setDistance(dao.getDistance());
			int srid = ((LineString) dao.getGeom().getGeometry()).getSrid();
			bo.setSrid(srid);
			String value = ((LineString) dao.getGeom().getGeometry())
					.getValue();
			bo.setValue(value);
			int type = ((LineString) dao.getGeom().getGeometry()).getType();
			bo.setType(type);
			String typeString = Geometry.getTypeString(type);
			bo.setTypeString(typeString);
			bo.setModtime(dao.getModtime());
			bo.setNumber(dao.getNumber());
			bo.setTimeinmillis(dao.getTimeinmillis());
			this.routingList.add(bo);
		}
	}

	public List<RoutingBo> getRoutingList() {
		return routingList;
	}

}
