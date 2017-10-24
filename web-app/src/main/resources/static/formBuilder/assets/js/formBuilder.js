jQuery(function ($) {

	var fields = [
		{
			label: "Gender",
			type: "gender"
		}, {
			label: "Birthdate",
			type: "birthdate"
		}, {
			label: "City",
			type: "city"
		}, {
			label: "Country",
			type: "country"
		}, {
			label: "Education",
			type: "education"
		}, {
			label: "Employed",
			type: "employed"
		}, {
			label: "Degree type",
			type: "degree_type"
		}, {
			label: "Spoken languages",
			type: "spoken_languages"
		}, {
			label: "Label",
			type: "label"
		}, {
			label: "Binary select (Yes, NO)",
			type: "binary_select"
		}, {
			label: "FirstName",
			type: "firstName"
		}, {
			label: "LastName",
			type: "lastName"
		}
	];
	var templates = {
		binary_select: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		label: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		firstName: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		lastName: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		birthdate: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		gender: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		city: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		country: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		spoken_languages: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		education: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		employed: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		},
		degree_type: function (fieldData) {
			return {
				field: '<label class="field-label">' + fieldData.type + '</label>'
			};
		}
	};


	var typeUserDisabledAttrs = {
		gender: ['value'],
		city: ['value'],
		country: ['value'],
		birthdate: ['value'],
		education: ['value'],
		employed: ['value'],
		degree_type: ['value'],
		spoken_languages: ['value'],
		label: ['required'],
		select: ['logo']

	};


	var fbOptions = {
		disableFields: [
			'autocomplete',
			'file',
			// 'text',
			'textarea',
			'hidden',
			'checkbox-group',
			'radio-group',
			// 'select',
			'number',
			'header',
			'button',
			'checkroom',
			'date',
			'starRating',
			'paragraph'
		],
		disabledAttrs: [
			"value",
			"placeholder",
			// "options",
			// "label",
			"multiple",
			"className",
			"name",
			"description",
			"subtype",
			"access",
			'maxlength'

		],
		subtypes: {
			text: ['datetime-local']
		},
		onSave: function (e, formData) {
			var formJson = JSON.parse(formData);
			var errorMessage = "<p  '> Field can not be empty </p>"
			var id = $('#id').val();
			var partnerId = $('#partnerId').val();
			var campaignId = $('#campaignId').val();
			var name = $('#name').val();
			var description = $('#description').val();
			var availability = $('#availability').val();
			var error = new Array();
			if (!partnerId) {
				error.push("partnerId");
			}
			if (!name) {
				error.push("name");
			}
			if (error.length > 0) {
				$("#errorPartner").css("display", "none");
				$("#errorName").css("display", "none");

				$.each(error, function (key, value) {
					if (value === "partnerId") {
						$('#errorPartner').html(errorMessage);
						$('#errorPartner').css("display", "block");

					} else if (value === "name") {
						$('#errorName').html(errorMessage);
						$('#errorName').css("display", "block");

					}
				});

			}
			else {

				// registration bean json
				var beanData = "{partnerId : " + partnerId;
				if (id) {
					beanData = beanData + ",id : " + id;
				}
				if (campaignId) {
					beanData = beanData + ", campaignId : " + campaignId;
				}
				if (name) {
					beanData = beanData + ", name : " + "'" + name + "'";
				}
				if (description) {
					beanData = beanData + ", description : " + "'" + description + "'";
				}
				if (availability) {
					beanData = beanData + ", availability : " + availability;
				}
				beanData = beanData + "}";

				// registration element json
				var resultData = JSON.stringify(formJson);

				var url = $("#regForm").attr("action");
				$.ajax({
					url: url,
					type: "POST",
					data: {jsonData: resultData, beanData: beanData},
					success: function (data) {
						if (data === "ok") {
							window.location.href = url;
						}
						else {
							$("#errorMessage").html(data);
							$("#errorMessage").addClass("alert");
						}

					}
				});
				return false;
			}

		},
		stickyControls: {
			enable: true
		},
		sortableControls: true,
		fields: fields,
		templates: templates,
		typeUserDisabledAttrs: typeUserDisabledAttrs,
		disableInjectedStyle: false
		// disabledAttrs
	};

	var formData = $('#jsonFormContent').val();

	if (formData) {
		fbOptions.formData = formData;
	}

	var formBuilder = $('.build-wrap').formBuilder(fbOptions);
});
