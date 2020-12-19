<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>いいねした人</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                        </tr>
                        <c:forEach var="reaction" items="${getAllGood}" varStatus="status">
                        <tr>
                            <td><c:out value="${reaction.employee.name}" /></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value='/reports/show?id=${report.id}' />">日報に戻る</a>
        </p>
    </c:param>
</c:import>