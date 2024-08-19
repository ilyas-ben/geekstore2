// Adjust the import path as necessary

import { request } from "./axios_helper";

export const getCurrentUsersCart = async () => {
    try {
        const response = await request('GET', `/carts/byuserid`);
        return response.data;
    } catch (error) {
        console.error('Error fetching cart by userId:', error);
    }
};

export const emptyCartByUserId = async (userId) => {
    try {
        await request('DELETE', `/carts/empty/byuserid/${userId}`);
    } catch (error) {
        console.error('Error emptying cart by userId:', error);
    }
};

export const setProductQuantityInCart = async (productId, quantity) => {
    try {
        await request('POST', '/carts/setProductQuantity', {
            productId,
            quantity,
        });
    } catch (error) {
        console.error('Error setting product quantity in cart by cartId:', error);
    }
};

export const addProductToCart = async (productId) => {
    try {
        const response = await request('post', '/carts/addProduct',productId);
        if (response.data) {
            Swal.fire({
                icon: 'success',
                title: 'Your product has been added!',
                text: 'You can change the quantity now in your cart menu.',
              });
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Your product has been already  added !',
                text: 'You can change the quantity in your cart menu.',
              });
        }
    } catch (error) {
        Swal.fire({
            icon: 'error',
            title: 'There was a problem from the server',
            text: 'Try again later',
          });
        console.error('Error adding product quantity in cart by cartId:', error);
    }
};




