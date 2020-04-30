package uniandes.isis2304.alohandes.negocio;

public class Incluye implements VOIncluye
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador de la oferta que incluye el servicio
	 */
	private long oferta;
	
	/**
	 * El identificador del servicio que se incluye en la oferta
	 */
	private long servicio;
	
	/**
	 * Indica si el servicio esta incluido en el precio.
	 */
	private int incluido;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Incluye () 
	{
		this.oferta = 0;
		this.servicio = 0;
		this.incluido = 0;
	}

	/**
	 * Constructor con valores
	 * @param idOferta - El identificador de la oferta. Debe exixtir un oferta con dicho identificador
	 * @param idServicio - El identificador del servicio. Debe existir una servicio con dicho identificador
	 * @param incluido - Si el servicio esta incluido en el precio
	 */
	public Incluye (long idOferta, long idServicio, int incluido) 
	{
		this.oferta = idOferta;
		this.servicio = idServicio;
		this.incluido = incluido;
	}

	/**
	 * @return El idOferta
	 */
	public long getOferta() 
	{
		return oferta;
	}

	/**
	 * @param idOferta - El nuevo identificador de oferta. Debe existir un oferta con dicho identificador
	 */
	public void setOferta(long idOferta) 
	{
		this.oferta = idOferta;
	}

	/**
	 * @return El idServicio
	 */
	public long getServicio() 
	{
		return servicio;
	}

	/**
	 * @param idServicio - El nuevo identificador de servicio. Debe existir una servicio con dicho identificador
	 */
	public void setServicio(long idServicio) 
	{
		this.servicio = idServicio;
	}

	/**
	 * @return Si el servicio esta incluido en el precio
	 */
	public int getIncluido() 
	{
		return incluido;
	}

	/**
	 * @param incluido - La nueva condicion del servicio con respecto a si esta incluido
	 */
	public void setIncluido(int incluido) 
	{
		this.incluido = incluido;
	}

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Sirven [idOferta=" + oferta + ", idServicio=" + servicio + ", incluido=" + incluido + "]";
	}
}
