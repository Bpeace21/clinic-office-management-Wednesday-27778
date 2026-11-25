import "./App.css";
import LibraryHeader from "./component/LibraryHeader.jsx";
import LibraryMain from "./component/LibraryMain.jsx";
import LibraryLogin from "./component/LibraryLogin.jsx";
import LibraryFooter from "./component/LibraryFooter.jsx";
import Office from "./component/Office.jsx";
import OfficeList from "./component/OfficeList.jsx";

function App() {
  return (
    <div>
      <LibraryHeader />
      <LibraryMain />
      <div className="card"><LibraryLogin /></div>
      <div className="card"><Office /></div>
      <div className="card"><OfficeList /></div>
      <LibraryFooter />
    </div>
  );
}

export default App;