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

package com.liferay.layout.uad.anonymizer;

import com.liferay.layout.uad.constants.LayoutUADConstants;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutPrototypeLocalService;

import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.List;

/**
 * Provides the base implementation for the layout prototype UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link LayoutPrototypeUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseLayoutPrototypeUADAnonymizer
	extends DynamicQueryUADAnonymizer<LayoutPrototype> {
	@Override
	public void autoAnonymize(LayoutPrototype layoutPrototype, long userId,
		User anonymousUser) throws PortalException {
		if (layoutPrototype.getUserId() == userId) {
			layoutPrototype.setUserId(anonymousUser.getUserId());
			layoutPrototype.setUserName(anonymousUser.getFullName());
		}

		layoutPrototypeLocalService.updateLayoutPrototype(layoutPrototype);
	}

	@Override
	public void delete(LayoutPrototype layoutPrototype)
		throws PortalException {
		layoutPrototypeLocalService.deleteLayoutPrototype(layoutPrototype);
	}

	@Override
	public String getApplicationName() {
		return LayoutUADConstants.APPLICATION_NAME;
	}

	@Override
	public List<String> getNonanonymizableFieldNames() {
		return Arrays.asList();
	}

	@Override
	public Class<LayoutPrototype> getTypeClass() {
		return LayoutPrototype.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return layoutPrototypeLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return LayoutUADConstants.USER_ID_FIELD_NAMES_LAYOUT_PROTOTYPE;
	}

	@Reference
	protected LayoutPrototypeLocalService layoutPrototypeLocalService;
}