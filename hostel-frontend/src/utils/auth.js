export const normalizeRole = (userLike) => {
  const role = userLike?.role || userLike?.authorities?.[0] || userLike?.roles?.[0] || userLike?.user?.role || '';
  return String(role).replace('ROLE_', '').toUpperCase();
};

export const extractToken = (data) => data?.token || data?.jwt || data?.accessToken || data?.access_token || '';
export const extractUser = (data) => data?.user || data?.data?.user || data;

export const roleHome = (role) => {
  if (role === 'STUDENT') return '/student';
  if (role === 'STAFF') return '/staff';
  if (role === 'ADMIN') return '/admin';
  return '/login';
};
