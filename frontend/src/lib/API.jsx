import axios from 'axios'

/// Some contants so we know what we're talking about
export const transactionTypes = {
  CREATE: 1,
  RETRIEVE: 2,
  UPDATE: 3,
  DELETE: 4,
}

/// A delay function so we can see if the optimistic UI is working
const delay = () => new Promise((res) => setTimeout(() => res(), 1800))

/// Setup the main URL
const api = axios.create({
  baseURL: 'http://localhost:3004',
})

/// This is the endpoint we are going to use. 
/// this endpoint also functions as a "key" for the SWR caching system
export const urlEndpoint = '/recipes'

/// R - Retreive
export const getData = async () => {
  await delay()
  const response = await api.get(urlEndpoint)
  return response.data
}

/// C - Create
export const addData = async (data) => {
  await delay()
  
  /// To ensure some transactions will fail!!
  if (Math.random() < 0.5) throw new Error('Failed to add new item!')
  
  const response = await api.post(urlEndpoint, data)
  return response.data
}

/// U - Update
export const updateData = async (data) => {
  await delay()
  const response = await api.patch(`${urlEndpoint}/${data.id}`, data)
  return response.data
}

/// D - Delete
export const deleteData = async ({ id }) => {
  await delay()
  const response = await api.delete(`${urlEndpoint}/${id}`)
  return response.data
}