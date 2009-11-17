<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/polls_display/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

questionId = ParamUtil.getLong(request, "questionId", questionId);

List questions = PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= NoSuchQuestionException.class %>" message="the-question-could-not-be-found" />

	<aui:fieldset>
		<aui:select label="question" name="questionId">
			<aui:option value="" />

			<%
			for (int i = 0; i < questions.size(); i++) {
				PollsQuestion question = (PollsQuestion)questions.get(i);

				question = question.toEscapedModel();
			%>

				<aui:option label="<%= question.getTitle(locale) %>" selected="<%= questionId == question.getQuestionId() %>" value="<%= question.getQuestionId() %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button name="saveButton" onClick='<%= "submitForm(document." + renderResponse.getNamespace() + "fm);" %>' type="button" value="save" />

		<aui:button name="cancelButton" onClick="<%= redirect %>" type="button" value="cancel" />
	</aui:button-row>
</aui:form>