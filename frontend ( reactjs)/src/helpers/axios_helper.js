import axios from 'axios';


export const getAuthToken = () => {
    return localStorage.getItem('auth_token');
};

export const setAuthHeader = (token) => {
    if (token !== null) {
        window.localStorage.setItem("auth_token", token);
    } else {
        window.localStorage.removeItem("auth_token");
    }
};

export const logout = () => {
    setAuthHeader(null);
    location.reload();
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "preventDuplicates": true,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
}

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json';

// Add a response interceptor
/* axios.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 401) {
            // Redirect to login page with error parameter
            alert("oui, response " + response.);
            l
        }
        return Promise.reject(error);
    }
); */

export const request = async (method, url, data) => {
    let headers = {};
    if (getAuthToken() !== null && getAuthToken() !== "null") {

        headers = { 'Authorization': `Bearer ${getAuthToken()}`, 'Content-Type': 'application/json' };
    }

    try {
        const response = await axios({
            method: method,
            url: url,
            headers: headers,
            data: data
        });
        return response;
    } catch (error) {
        console.error(error);
        const status = error.response.status;
        console.log(status);
        if (status === 401) {
            if (location.pathname === "/login") {
                location.href = '/login?error=2';
            }
            else location.href = '/login?error=1';
        }
    }



};
