import { useState, useEffect } from "react";
import axios from "axios";
import LibraryButton from "./LibraryButton";

const API_URL = "http://localhost:8080/api/offices";

function OfficeList() {
  // all offices that come from the backend
  const [offices, setOffices] = useState([]);
  // what the user types in the search box
  const [search, setSearch] = useState("");
  // message shown on the page if the backend cannot be reached
  const [errorMessage, setErrorMessage] = useState("");

  // get all offices from the backend
  async function loadOffices() {
    try {
      const response = await axios.get(API_URL);
      setOffices(response.data);
      setErrorMessage("");
    } catch (error) {
      setErrorMessage(
        "Could not load offices: " +
          error.message +
          ". Is the backend running on " +
          API_URL +
          " ?"
      );
    }
  }

  // run loadOffices one time when the page opens
  useEffect(() => {
    loadOffices();
  }, []);

  // delete one office using its id
  async function deleteOffice(id) {
    const sure = window.confirm("Are you sure you want to delete this office?");
    if (!sure) {
      return;
    }
    try {
      const response = await axios.delete(API_URL + "/" + id);
      if (response.status == 204) {
        alert("Office deleted successfully");
        loadOffices(); // reload the table
      } else {
        alert("Office is not deleted");
      }
    } catch (error) {
      if (error.response && error.response.status == 409) {
        alert("Cannot delete: this office is assigned to a doctor");
      } else {
        alert("This is the error: " + error.message);
      }
    }
  }

  // update will be done in the next class
  function updateOffice() {
    alert("Update will be done in the next class");
  }

  // keep only the offices that match the search text (name OR number)
  const filteredOffices = offices.filter(
    (o) =>
      o.name.toLowerCase().includes(search.toLowerCase()) ||
      String(o.officeNumber).includes(search)
  );

  return (
    <div>
      <h1>ALL OFFICES</h1>

      <label>SEARCH : </label>
      <input
        type="text"
        placeholder="Office name or office number"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <LibraryButton buttonLabel="REFRESH" onClickButton={loadOffices} />

      {errorMessage != "" && <div className="error">{errorMessage}</div>}

      <table>
        <thead>
          <tr>
            <th>Office Name</th>
            <th>Office Number</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {filteredOffices.length == 0 ? (
            <tr>
              <td colSpan="3">No office found</td>
            </tr>
          ) : (
            filteredOffices.map((office) => (
              <tr key={office.id}>
                <td>{office.name}</td>
                <td>{office.officeNumber}</td>
                <td>
                  <LibraryButton
                    buttonLabel="Update"
                    onClickButton={updateOffice}
                  />
                  <LibraryButton
                    buttonLabel="Delete"
                    onClickButton={() => deleteOffice(office.id)}
                  />
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default OfficeList;
