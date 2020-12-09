/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package daniilk.example;

import daniilk.example.repository.ContactRepositoryMock;
import daniilk.example.service.ContactService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

public class ContactServiceTests {

    @Autowired
    private ContactService contactService;

    @Before
    public void setup(){
        ContactRepositoryMock contactRepositoryMock = new ContactRepositoryMock();
        contactService = new ContactService(contactRepositoryMock);
        ReflectionTestUtils.setField(contactService, "contactRepositoryMock", contactRepositoryMock);
    }

	@Test
	public void listAllContactsTest() throws Exception {
        setup();
        Assertions.assertTrue(contactService.listAllContacts().size() > 0); ;
    }

    @Test
    public void searchContactsByNameTest() throws Exception {
        setup();
        Assertions.assertTrue(contactService.searchContactsByName("Homer Simpson").size() > 0);
    }


}
