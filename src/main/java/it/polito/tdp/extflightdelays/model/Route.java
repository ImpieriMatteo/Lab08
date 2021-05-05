package it.polito.tdp.extflightdelays.model;

public class Route {
	
	private Airport partenza;
	private Airport arrivo;
	private double distanzaMedia;
	
	public Route(Airport partenza, Airport arrivo, double distanzaMedia) {
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.distanzaMedia = distanzaMedia;
	}

	public Airport getPartenza() {
		return partenza;
	}

	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}

	public Airport getArrivo() {
		return arrivo;
	}

	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}

	public double getDistanzaMedia() {
		return distanzaMedia;
	}

	public void setDistanzaMedia(double distanzaMedia) {
		this.distanzaMedia = distanzaMedia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivo == null) ? 0 : arrivo.hashCode());
		result = prime * result + ((partenza == null) ? 0 : partenza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (arrivo == null) {
			if (other.arrivo != null)
				return false;
		} else if (!arrivo.equals(other.arrivo))
			return false;
		if (partenza == null) {
			if (other.partenza != null)
				return false;
		} else if (!partenza.equals(other.partenza))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [" + partenza.getAirportName() + ", " + arrivo.getAirportName() + " ]" + distanzaMedia + "\n";
	}

}
