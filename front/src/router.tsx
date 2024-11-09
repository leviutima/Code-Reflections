import { Route, Routes } from "react-router-dom";
import { Home } from "./pages/home";

export function Router() {
    return(
        <Routes>
            <Route>
                <Route path="/" element={<Home />}/>
            </Route>
        </Routes>
    )
}