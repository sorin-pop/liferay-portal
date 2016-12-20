/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.mail;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Adolfo Pérez
 */
public class MailTemplateFactoryUtil {

	public static MailTemplate createMailTemplate(
		String template, boolean escapeHtml) {

		return getMailTemplateFactory().createMailTemplate(
			template, escapeHtml);
	}

	public static MailTemplateContextBuilder
		createMailTemplateContextBuilder() {

		return getMailTemplateFactory().createMailTemplateContextBuilder();
	}

	public static MailTemplateFactory getMailTemplateFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			MailTemplateFactoryUtil.class);

		return _mailTemplateFactory;
	}

	private static volatile MailTemplateFactory _mailTemplateFactory =
		ServiceProxyFactory.newServiceTrackedInstance(
			MailTemplateFactory.class, MailTemplateFactoryUtil.class,
			"_mailTemplateFactory", false);

}