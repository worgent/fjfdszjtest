<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
<xsl:template match="/">
<xsl:apply-templates select="MenuNodes"/>
</xsl:template>
<xsl:template match="MenuNodes">
	<TABLE cellspacing="0" cellpadding="0" class="2k3MenuBar" id="myMenuBar">
		<tr>
			<td class="2k3MenuBarHandle"><div></div><div></div><div></div><div></div><div></div><div></div><div></div></td>
			<xsl:for-each select="./MenuNode">
			<td class="2k3MenuRoot">
				<xsl:attribute name="id">
					<xsl:value-of select="@id"/>
				</xsl:attribute>
				<xsl:if test="@submenu">
					<xsl:attribute name="menu">
						<xsl:value-of select="@submenu"/>
					</xsl:attribute>						
				</xsl:if>
				<xsl:if test="@onclick">
					<xsl:attribute name="onclick">
						<xsl:value-of select="@onclick"/>
					</xsl:attribute>						
				</xsl:if>
				<div class="2k3MenuRootCaption">
				<xsl:if test="@title">
				<xsl:attribute name="title">
					<xsl:value-of select="@title"/>
				</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="@text"/>
				</div>
			</td>
			</xsl:for-each>
		</tr>
	</TABLE>
</xsl:template>
</xsl:stylesheet>