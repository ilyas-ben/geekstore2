import { useEffect, useState } from "react";
import { getAuthToken, logout } from "./helpers/axios_helper";
import { jwtDecode } from "jwt-decode";
import { getCurrentUsersCart } from "./helpers/cart";
 // Corrected the import

function Header() {
    const [token, setToken] = useState(null); // Changed default to `null`
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        const tokenFromStorage = getAuthToken();
        if (tokenFromStorage !== "undefined" && tokenFromStorage) {
            setToken(jwtDecode(tokenFromStorage));
        } else {
            setToken(null);
        }
    }, []); // Empty dependency array ensures this runs only once on mount

    const fetchCart = async () => {
        const cartData = await getCurrentUsersCart();
        if (cartData) {
            setCartItems(cartData.cartItems);
        }
    };

    return (
        <>
            <header>
                <nav className="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
                    <div className="container-fluid">
                        <a className="navbar-brand" href="/">ILOUSE.MA</a>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarText">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <a className="nav-link" aria-current="page" href="/">Home</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="/categories">Categories</a>
                                </li>
                                <li className="nav-item">
                                    <a
                                        className="nav-link"
                                        data-bs-toggle="modal"
                                        data-bs-target="#cartModal"
                                        href="#"
                                        onClick={fetchCart} // Fetch cart items when the "Cart" link is clicked
                                    >
                                        Cart
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="/aboutme">About me</a>
                                </li>
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Dropdown link
                                    </a>
                                    <ul className="dropdown-menu">
                                        <li><a className="dropdown-item" href="#">Action</a></li>
                                        <li><a className="dropdown-item" href="#">Another action</a></li>
                                        <li><a className="dropdown-item" href="#">Something else here</a></li>
                                    </ul>
                                </li>
                            </ul>
                            {token ? (
                                <>  
                                    <a className="nav-link text-light my-1" aria-current="page" href="#">{token.sub}</a>
                                    <a href="#" className="btn btn-danger" onClick={logout}>Logout</a>
                                </>
                            ) : (
                                <a href="/login" className="btn btn-success">Sign in</a>
                            )}
                        </div>
                    </div>
                </nav>
            </header>

            <div className="modal fade" id="cartModal" tabIndex="-1" aria-labelledby="cartModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="cartModalLabel">Shopping Cart</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <ul className="list-group">
                                {cartItems.length > 0 ? (
                                    cartItems.map((item, index) => (
                                        <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                                            {item.quantity} x {item.product.name}
                                            <span className="badge bg-primary rounded-pill">USD{item.product.price * item.quantity}</span>
                                        </li>
                                    ))
                                ) : (
                                    <li className="list-group-item">Your cart is empty.</li>
                                )}
                            </ul>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary">Checkout</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Header;
