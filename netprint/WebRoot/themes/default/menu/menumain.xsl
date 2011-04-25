<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
<xsl:template match="/">
<xsl:apply-templates select="MenuNodes"/>
</xsl:template>
<xsl:template match="MenuNodes" >
	<TABLE border="0"  cellspacing="0" cellpadding="0" class="2k3MainMenu">
		<xsl:attribute name="id">
			<xsl:value-of select="@id"/>
		</xsl:attribute>
<thead><tr><td colspan="5"><div class="2k3MenuRootConnector"></div></td></tr></thead>
<tbody>
<xsl:for-each select="./MenuNode">
	<xsl:choose>
		<xsl:when test="@Seperator">
			<tr class="2k3MenuSeperator">
				<td class="2k3MenuImage" colspan="2"></td>
				<td class="2k3MenuCaption" colspan="3"><img/></td>
			</tr>
		</xsl:when>
		<xsl:otherwise>
			<tr class="2k3MenuItem">
				<xsl:if test="@submenu">
					<xsl:attribute name="subMenu">
						<xsl:value-of select="@submenu"/>
					</xsl:attribute>						
				</xsl:if>
				<xsl:if test="@title">
					<xsl:attribute name="title">
						<xsl:value-of select="@title"/>
					</xsl:attribute>						
				</xsl:if>
				<xsl:if test="@onclick">
					<xsl:attribute name="onclick">
						<xsl:value-of select="@onclick"/>
					</xsl:attribute>						
				</xsl:if>
				<td class="2k3MenuVerticalSpacerLeft"></td>
				<td class="2k3MenuImage"><span class="2k3MenuImageShadow" width="16" height="16" >
					<img width="16" height="16">
						<xsl:attribute name="src">
							<xsl:value-of select="@img"/>
						</xsl:attribute>
					</img>
					</span></td>
				<td class="2k3MenuCaption"><xsl:value-of select="@text"/></td>
				<xsl:choose>
					<xsl:when test="@havSub">
						<td class="2k3MenuMore">4</td>
					</xsl:when>
					<xsl:otherwise>
						<td class="2k3MenuNoMore">4</td>
					</xsl:otherwise>
				</xsl:choose>
				<td class="2k3MenuVerticalSpacerRight"></td>
			</tr>
		</xsl:otherwise>
	</xsl:choose>
</xsl:for-each>
		<xsl:choose>
			<xsl:when test="@root">
				<tr><td colspan="2" class="2k3BottomHead"></td><td colspan="3" class="2k3BottomBody"></td></tr>
			</xsl:when>
		</xsl:choose>
</tbody>
<tfoot><tr><td colspan="5"></td></tr></tfoot>

	</TABLE>
</xsl:template>
</xsl:stylesheet>