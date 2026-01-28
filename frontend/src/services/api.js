import request from './request';

export const fetchHome = (limit = 6) => request.get('/home', { limit });

export const searchAll = (keyword, limit = 6) => request.get('/search', { keyword, limit });

export const fetchAttractions = (limit = 10) => request.get('/attractions', { limit });

export const fetchAttractionDetail = (id) => request.get(`/attractions/${id}`);

export const fetchFoods = (limit = 10) => request.get('/foods', { limit });

export const fetchFoodDetail = (id) => request.get(`/foods/${id}`);

export const fetchTopics = (limit = 10) => request.get('/topics', { limit });

export const fetchNotes = (topicId, limit = 10) =>
  request.get('/notes', topicId ? { topicId, limit } : { limit });

export const createNote = (payload) => request.post('/notes', payload);

export const fetchItineraries = (userId) => request.get('/itineraries', { userId });

export const fetchItineraryDetail = (id) => request.get(`/itineraries/${id}`);

export const fetchUserProfile = (userId) => request.get(`/users/${userId}/profile`);

export const fetchUserFavorites = (userId, targetType) =>
  request.get(`/users/${userId}/favorites`, targetType ? { targetType } : {});

export const fetchUserLikes = (userId, targetType) =>
  request.get(`/users/${userId}/likes`, targetType ? { targetType } : {});
