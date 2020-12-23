<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                                </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td><fmt:formatDate value="${report.created_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${report.updated_at}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <!-- 編集者以外はエディット画面が出ないようにする -->
                    <p>
                        <a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a>
                    </p>
                </c:if>

                <c:choose>
                    <c:when test="${empty goodCheck }">
                        <p>
                            <a href="#" onclick="infGoodSet();">いいね👍</a>
                             : <a href="<c:url value='/reactions/good/show?id=${report.id}'/>">${good_count}</a>
                         </p>
                    </c:when>
                    <c:otherwise>
                        <p>
                            <a href="#" onclick="infGoodReset();">いいね取り消し</a>
                             : <a href="<c:url value='/reactions/good/show?id=${report.id}'/>">${good_count}</a>
                        </p>

                    </c:otherwise>
                </c:choose>
                <form method="POST" action="<c:url value='/reactions/good/set'/>">
                    <input type="hidden" name="_token" value="${_token}" /> <input
                        type="hidden" name="report_id" value="${report.id}" />
                </form>

                <form method="POST" action="<c:url value='/reactions/good/reset'/>">
                    <input type="hidden" name="_token" value="${_token}" /> <input
                        type="hidden" name="report_id" value="${report.id}" />
                </form>

                <script>
                        function infGoodSet(){
                            if(confirm("いいねしました。")){
                                document.forms[0].submit();
                            }
                        }
                            function infGoodReset(){
                                if(confirm("いいねを取り消しました。")){
                                    document.forms[1].submit();
                                }
                            }
                    </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value="/reports/index" />">日報一覧に戻る</a>
        </p>
    </c:param>
</c:import>