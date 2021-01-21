export const TOKEN_KEY = 'token-restwithspringboot';

export const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null;

export const getToken = () => localStorage.getItem(TOKEN_KEY);

export const doLogin = (token) => {
  localStorage.setItem(TOKEN_KEY,token);
};

export const doLogout = () => {
  localStorage.removeItem(TOKEN_KEY);
};
