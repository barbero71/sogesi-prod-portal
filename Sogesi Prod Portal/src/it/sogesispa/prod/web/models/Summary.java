package it.sogesispa.prod.web.models;

public class Summary
{
	private Integer stabId;
	private String stabName;
	private double oreTotali;
	private double oreTotaliDip;
	private double oreTotaliSom;
	private double kgLavorati;
	private double kgPiana;
	private double kgCotone;
	private double kgPolicotone;
	private double kgHv;
	private double kgMaterassi;
	private double kgTtr;
	private double percPiana;
	private double percCotone;
	private double percPolicotone;
	private double percHv;
	private double percMaterassi;
	private double percTtr;
	private double prodIndex;
	private double orePiana;
	private double oreCotone;
	private double oreHv;
	private double oreMaterassi;
	private double oreTtr;
	private double oreGen;
	private double orePianaDip;
	private double oreCotoneDip;
	private double oreHvDip;
	private double oreMaterassiDip;
	private double oreTtrDip;
	private double oreGenDip;
	private double orePianaSom;
	private double oreCotoneSom;
	private double oreHvSom;
	private double oreMaterassiSom;
	private double oreTtrSom;
	private double oreGenSom;
	//****ORE CENTRI DI COSTO NON RAGGRUPPATI****
	private double oreDipCdc0001;
	private double oreDipCdc0007;
	private double oreDipCdc0008;
	private double oreDipCdc0013;
	private double oreDipCdc0014;
	private double oreDipCdc0015;
	private double oreDipCdc0016;
	private double oreDipCdc0027;
	private double oreSomCdc0001;
	private double oreSomCdc0007;
	private double oreSomCdc0008;
	private double oreSomCdc0013;
	private double oreSomCdc0014;
	private double oreSomCdc0015;
	private double oreSomCdc0016;
	private double oreSomCdc0027;
	
	public double getOreGen() {
		return oreGen;
	}

	public void setOreGen(double oreGen) {
		this.oreGen = oreGen;
	}

	public double getOreGenDip() {
		return oreGenDip;
	}

	public void setOreGenDip(double oreGenDip) {
		this.oreGenDip = oreGenDip;
	}

	public double getOreGenSom() {
		return oreGenSom;
	}

	public void setOreGenSom(double oreGenSom) {
		this.oreGenSom = oreGenSom;
	}

	public Integer getStabId()
	{
		return stabId;
	}
	
	public void setStabId(Integer stabId)
	{
		this.stabId = stabId;
	}
	
	public String getStabName()
	{
		return stabName;
	}
	
	public void setStabName(String stabName)
	{
		this.stabName = stabName;
	}
	
	public double getOreTotali()
	{
		return oreTotali;
	}
	
	public double getOreTotaliDip() {
		return oreTotaliDip;
	}

	public void setOreTotaliDip(double oreTotaliDip) {
		this.oreTotaliDip = oreTotaliDip;
	}

	public double getOreTotaliSom() {
		return oreTotaliSom;
	}

	public void setOreTotaliSom(double oreTotaliSom) {
		this.oreTotaliSom = oreTotaliSom;
	}

	public void setOreTotali(double d)
	{
		this.oreTotali = d;
	}
	
	public double getKgLavorati()
	{
		return kgLavorati;
	}
	
	public void setKgLavorati(double kgLavorati)
	{
		this.kgLavorati = kgLavorati;
	}
	
	public double getKgPiana()
	{
		return kgPiana;
	}
	
	public void setKgPiana(double kgPiana)
	{
		this.kgPiana = kgPiana;
	}
	
	public double getKgCotone()
	{
		return kgCotone;
	}
	
	public void setKgCotone(double kgCotone)
	{
		this.kgCotone = kgCotone;
	}
	
	public double getKgPolicotone()
	{
		return kgPolicotone;
	}
	
	public void setKgPolicotone(double kgPolicotone)
	{
		this.kgPolicotone = kgPolicotone;
	}
	
	public double getKgHv()
	{
		return kgHv;
	}
	
	public void setKgHv(double kgHv)
	{
		this.kgHv = kgHv;
	}
	
	public double getKgMaterassi()
	{
		return kgMaterassi;
	}
	
	public void setKgMaterassi(double kgMaterassi)
	{
		this.kgMaterassi = kgMaterassi;
	}
	
	public double getKgTtr()
	{
		return kgTtr;
	}
	
	public void setKgTtr(double kgTtr)
	{
		this.kgTtr = kgTtr;
	}
	
	public double getPercPiana()
	{
		return percPiana;
	}

	public void setPercPiana(double percPiana)
	{
		this.percPiana = percPiana;
	}

	public double getPercCotone()
	{
		return percCotone;
	}

	public void setPercCotone(double percCotone)
	{
		this.percCotone = percCotone;
	}

	public double getPercPolicotone()
	{
		return percPolicotone;
	}

	public void setPercPolicotone(double percPolicotone)
	{
		this.percPolicotone = percPolicotone;
	}

	public double getPercHv()
	{
		return percHv;
	}

	public void setPercHv(double percHv)
	{
		this.percHv = percHv;
	}

	public double getPercMaterassi()
	{
		return percMaterassi;
	}

	public void setPercMaterassi(double percMaterassi)
	{
		this.percMaterassi = percMaterassi;
	}
	
	public double getPercTtr()
	{
		return percTtr;
	}

	public void setPercTtr(double percTtr)
	{
		this.percTtr = percTtr;
	}
	
	public double getProdIndex()
	{
		return prodIndex;
	}

	public void setProdIndex(double prodIndex)
	{
		this.prodIndex = prodIndex;
	}

	public double getOrePiana() {
		return orePiana;
	}

	public void setOrePiana(double orePiana) {
		this.orePiana = orePiana;
	}

	public double getOreCotone() {
		return oreCotone;
	}

	public void setOreCotone(double oreCotone) {
		this.oreCotone = oreCotone;
	}

	public double getOreHv() {
		return oreHv;
	}

	public void setOreHv(double oreHv) {
		this.oreHv = oreHv;
	}

	public double getOreMaterassi() {
		return oreMaterassi;
	}

	public void setOreMaterassi(double oreMaterassi) {
		this.oreMaterassi = oreMaterassi;
	}

	public double getOreTtr() {
		return oreTtr;
	}

	public void setOreTtr(double oreTtr) {
		this.oreTtr = oreTtr;
	}

	public double getOrePianaDip() {
		return orePianaDip;
	}

	public void setOrePianaDip(double orePianaDip) {
		this.orePianaDip = orePianaDip;
	}

	public double getOreCotoneDip() {
		return oreCotoneDip;
	}

	public void setOreCotoneDip(double oreCotoneDip) {
		this.oreCotoneDip = oreCotoneDip;
	}

	public double getOreHvDip() {
		return oreHvDip;
	}

	public void setOreHvDip(double oreHvDip) {
		this.oreHvDip = oreHvDip;
	}

	public double getOreMaterassiDip() {
		return oreMaterassiDip;
	}

	public void setOreMaterassiDip(double oreMaterassiDip) {
		this.oreMaterassiDip = oreMaterassiDip;
	}

	public double getOreTtrDip() {
		return oreTtrDip;
	}

	public void setOreTtrDip(double oreTtrDip) {
		this.oreTtrDip = oreTtrDip;
	}

	public double getOrePianaSom() {
		return orePianaSom;
	}

	public void setOrePianaSom(double orePianaSom) {
		this.orePianaSom = orePianaSom;
	}

	public double getOreCotoneSom() {
		return oreCotoneSom;
	}

	public void setOreCotoneSom(double oreCotoneSom) {
		this.oreCotoneSom = oreCotoneSom;
	}

	public double getOreHvSom() {
		return oreHvSom;
	}

	public void setOreHvSom(double oreHvSom) {
		this.oreHvSom = oreHvSom;
	}

	public double getOreMaterassiSom() {
		return oreMaterassiSom;
	}

	public void setOreMaterassiSom(double oreMaterassiSom) {
		this.oreMaterassiSom = oreMaterassiSom;
	}

	public double getOreTtrSom() {
		return oreTtrSom;
	}

	public void setOreTtrSom(double oreTtrSom) {
		this.oreTtrSom = oreTtrSom;
	}
	
	public double getOreDipCdc0001() {
		return oreDipCdc0001;
	}

	public void setOreDipCdc0001(double oreDipCdc0001) {
		this.oreDipCdc0001 = oreDipCdc0001;
	}

	public double getOreDipCdc0007() {
		return oreDipCdc0007;
	}

	public void setOreDipCdc0007(double oreDipCdc0007) {
		this.oreDipCdc0007 = oreDipCdc0007;
	}

	public double getOreDipCdc0008() {
		return oreDipCdc0008;
	}

	public void setOreDipCdc0008(double oreDipCdc0008) {
		this.oreDipCdc0008 = oreDipCdc0008;
	}

	public double getOreDipCdc0013() {
		return oreDipCdc0013;
	}

	public void setOreDipCdc0013(double oreDipCdc0013) {
		this.oreDipCdc0013 = oreDipCdc0013;
	}
	
	public double getOreDipCdc0014() {
		return oreDipCdc0014;
	}

	public void setOreDipCdc0014(double oreDipCdc0014) {
		this.oreDipCdc0014 = oreDipCdc0014;
	}

	public double getOreDipCdc0015() {
		return oreDipCdc0015;
	}

	public void setOreDipCdc0015(double oreDipCdc0015) {
		this.oreDipCdc0015 = oreDipCdc0015;
	}

	public double getOreDipCdc0016() {
		return oreDipCdc0016;
	}

	public void setOreDipCdc0016(double oreDipCdc0016) {
		this.oreDipCdc0016 = oreDipCdc0016;
	}

	public double getOreDipCdc0027() {
		return oreDipCdc0027;
	}

	public void setOreDipCdc0027(double oreDipCdc0027) {
		this.oreDipCdc0027 = oreDipCdc0027;
	}

	public double getOreSomCdc0001() {
		return oreSomCdc0001;
	}

	public void setOreSomCdc0001(double oreSomCdc0001) {
		this.oreSomCdc0001 = oreSomCdc0001;
	}

	public double getOreSomCdc0007() {
		return oreSomCdc0007;
	}

	public void setOreSomCdc0007(double oreSomCdc0007) {
		this.oreSomCdc0007 = oreSomCdc0007;
	}

	public double getOreSomCdc0008() {
		return oreSomCdc0008;
	}

	public void setOreSomCdc0008(double oreSomCdc0008) {
		this.oreSomCdc0008 = oreSomCdc0008;
	}
	
	public double getOreSomCdc0013() {
		return oreSomCdc0013;
	}

	public void setOreSomCdc0013(double oreSomCdc0013) {
		this.oreSomCdc0013 = oreSomCdc0013;
	}
	
	public double getOreSomCdc0014() {
		return oreSomCdc0014;
	}

	public void setOreSomCdc0014(double oreSomCdc0014) {
		this.oreSomCdc0014 = oreSomCdc0014;
	}

	public double getOreSomCdc0015() {
		return oreSomCdc0015;
	}

	public void setOreSomCdc0015(double oreSomCdc0015) {
		this.oreSomCdc0015 = oreSomCdc0015;
	}

	public double getOreSomCdc0016() {
		return oreSomCdc0016;
	}

	public void setOreSomCdc0016(double oreSomCdc0016) {
		this.oreSomCdc0016 = oreSomCdc0016;
	}

	public double getOreSomCdc0027() {
		return oreSomCdc0027;
	}

	public void setOreSomCdc0027(double oreSomCdc0027) {
		this.oreSomCdc0027 = oreSomCdc0027;
	}

	@Override
	public String toString()
	{
		return "Summary [stabId=" + stabId + ", stabName=" + stabName
				+ ", oreTotali=" + oreTotali + "]";
	}

}
