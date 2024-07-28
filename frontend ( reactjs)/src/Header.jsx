import { useEffect, useState } from "react";
import { getAuthToken, logout } from "./helpers/axios_helper";
import { jwtDecode } from "jwt-decode";




function Header() {
    const [token, setToken] = useState('null');
    useEffect(() => {
        if (getAuthToken() !== "undefined") {
            setToken(getAuthToken() ? (jwtDecode(getAuthToken())) : null);
        }
        else {
            setToken(null);
        }

    }, []);

    return (
        <>
            <header>
                <nav class="navbar navbar-expand-lg  bg-primary " data-bs-theme="dark">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="/">ILOUSE.MA</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarText">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="/">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/categories">Categories</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-bs-toggle="modal" data-bs-target="#cartModal"
                                        href="#">Cart</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link"
                                        href="/aboutme">About me</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Dropdown link
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Action</a></li>
                                        <li><a class="dropdown-item" href="#">Another action</a></li>
                                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                                    </ul>
                                </li>
                            </ul>
                            {token !== null ? (
                                <>
                                    <a className="nav-link text-light my-1" aria-current="page" href="#">{token.sub}</a>
                                    <a href="#" className="btn btn-danger" onClick={logout} /* onClick={logout()} */>Logout</a>
                                </>
                            ) : (
                                <a href="/login" className="btn btn-success">Sign in</a>
                            )}

                        </div>
                    </div>
                </nav>
            </header>


            <div class="modal fade" id="cartModal" tabindex="-1" aria-labelledby="cartModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="cartModalLabel">Shopping Cart</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <ul class="list-group">
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Product 1
                                    <span class="badge bg-primary rounded-pill">$10</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Product 2
                                    <span class="badge bg-primary rounded-pill">$15</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Product 3
                                    <span class="badge bg-primary rounded-pill">$20</span>
                                </li>
                            </ul>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Checkout</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Header