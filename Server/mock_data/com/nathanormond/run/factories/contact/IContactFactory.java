package com.nathanormond.run.factories.contact;

import com.nathanormond.model.data.reference_types.implementations.Contact;

public interface IContactFactory {
	Contact createContactFromLine(String line);
}
