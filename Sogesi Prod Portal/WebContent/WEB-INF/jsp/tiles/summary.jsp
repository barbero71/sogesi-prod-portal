<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${stabPon > 0}">
Stabilimento di Ponsacco<br/>
Ore lavorate:<c:out value="${orePon.oreTotali}"></c:out>
</c:if>
<br />
<div style="width: 100%; float: left;">&nbsp;</div>
<c:if test="${stabPsg > 0}">
Stabilimento di Ponte San Giovanni<br/>
Ore lavorate:<c:out value="${orePsg.oreTotali}"></c:out>
</c:if>
<br />
<div style="width: 100%; float: left;">&nbsp;</div>
<c:if test="${stabTer > 0}">
Stabilimento di Stroncone<br/>
Ore lavorate:<c:out value="${oreTer.oreTotali}"></c:out>
</c:if>
<br />
