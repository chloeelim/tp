package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

class RemarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        final String remarkString = "edited remark";
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // expected result
        Person expectedEditedPerson = new PersonBuilder(personToEdit).withRemark(remarkString).build();
        String expectedSuccessMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, expectedEditedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs()); // copy model
        expectedModel.setPerson(personToEdit, expectedEditedPerson);

        // actual result
        Remark remarkEdit = new Remark(remarkString);
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, remarkEdit);

        // assert Success Message and Model are as expected
        assertCommandSuccess(remarkCommand, model, expectedSuccessMessage, expectedModel);
    }
}
