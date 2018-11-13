package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.RollbackFailureException;
import entidad.CarritoProducto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.Carritos;
import entidad.Productos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CarritoProductoJpaController implements Serializable {

    public CarritoProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarritoProducto carritoProducto) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            utx = em.getTransaction();
            utx.begin();
            Carritos idCarrito = carritoProducto.getIdCarrito();
            if (idCarrito != null) {
                idCarrito = em.getReference(idCarrito.getClass(), idCarrito.getIdCarrito());
                carritoProducto.setIdCarrito(idCarrito);
            }
            Productos idProducto = carritoProducto.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                carritoProducto.setIdProducto(idProducto);
            }
            em.persist(carritoProducto);
            if (idCarrito != null) {
                idCarrito.getCarritoProductoList().add(carritoProducto);
                idCarrito = em.merge(idCarrito);
            }
            if (idProducto != null) {
                idProducto.getCarritoProductoList().add(carritoProducto);
                idProducto = em.merge(idProducto);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CarritoProducto carritoProducto) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            utx = em.getTransaction();
            utx.begin();
            CarritoProducto persistentCarritoProducto = em.find(CarritoProducto.class, carritoProducto.getIdCarritoProducto());
            Carritos idCarritoOld = persistentCarritoProducto.getIdCarrito();
            Carritos idCarritoNew = carritoProducto.getIdCarrito();
            Productos idProductoOld = persistentCarritoProducto.getIdProducto();
            Productos idProductoNew = carritoProducto.getIdProducto();
            if (idCarritoNew != null) {
                idCarritoNew = em.getReference(idCarritoNew.getClass(), idCarritoNew.getIdCarrito());
                carritoProducto.setIdCarrito(idCarritoNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                carritoProducto.setIdProducto(idProductoNew);
            }
            carritoProducto = em.merge(carritoProducto);
            if (idCarritoOld != null && !idCarritoOld.equals(idCarritoNew)) {
                idCarritoOld.getCarritoProductoList().remove(carritoProducto);
                idCarritoOld = em.merge(idCarritoOld);
            }
            if (idCarritoNew != null && !idCarritoNew.equals(idCarritoOld)) {
                idCarritoNew.getCarritoProductoList().add(carritoProducto);
                idCarritoNew = em.merge(idCarritoNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getCarritoProductoList().remove(carritoProducto);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getCarritoProductoList().add(carritoProducto);
                idProductoNew = em.merge(idProductoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carritoProducto.getIdCarritoProducto();
                if (findCarritoProducto(id) == null) {
                    throw new NonexistentEntityException("The carritoProducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            utx = em.getTransaction();
            utx.begin();
            CarritoProducto carritoProducto;
            try {
                carritoProducto = em.getReference(CarritoProducto.class, id);
                carritoProducto.getIdCarritoProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carritoProducto with id " + id + " no longer exists.", enfe);
            }
            Carritos idCarrito = carritoProducto.getIdCarrito();
            if (idCarrito != null) {
                idCarrito.getCarritoProductoList().remove(carritoProducto);
                idCarrito = em.merge(idCarrito);
            }
            Productos idProducto = carritoProducto.getIdProducto();
            if (idProducto != null) {
                idProducto.getCarritoProductoList().remove(carritoProducto);
                idProducto = em.merge(idProducto);
            }
            em.remove(carritoProducto);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarritoProducto> findCarritoProductoEntities() {
        return findCarritoProductoEntities(true, -1, -1);
    }

    public List<CarritoProducto> findCarritoProductoEntities(int maxResults, int firstResult) {
        return findCarritoProductoEntities(false, maxResults, firstResult);
    }

    private List<CarritoProducto> findCarritoProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarritoProducto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CarritoProducto findCarritoProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarritoProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarritoProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarritoProducto> rt = cq.from(CarritoProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CarritoProducto> obtenerCarrito(Carritos idCarrito) {
        List<CarritoProducto> compras;
        EntityManager em = getEntityManager();
        System.out.println("Buscando lista de compras de carrito : " + idCarrito);
        Query consulta = em.createNamedQuery("CarritoProducto.findByTipoCompra");
        consulta.setParameter("tipoCompra", 1);
        compras = consulta.getResultList();
        for (int i = 0; i < compras.size(); i++) {
            System.out.println(compras.get(i));
        }
        for (int i = 0; i < compras.size(); i++) {
            if (!compras.get(i).getIdCarrito().equals(idCarrito)) {
                System.out.println("Borre " + compras.get(i));
                compras.remove(i);
                i--;
            }
        }
        return compras;
    }

    public List<CarritoProducto> obtenerCarritoR(Carritos idCarrito) {
        List<CarritoProducto> compras;
        EntityManager em = getEntityManager();
        System.out.println("Buscando lista de compras de carrito : " + idCarrito);
        Query consulta = em.createNamedQuery("CarritoProducto.findByTipoCompra");
        consulta.setParameter("tipoCompra", 2);
        compras = consulta.getResultList();
        for (int i = 0; i < compras.size(); i++) {
            System.out.println(compras.get(i));
        }
        for (int i = 0; i < compras.size(); i++) {
            if (!compras.get(i).getIdCarrito().equals(idCarrito)) {
                System.out.println("Borre " + compras.get(i));
                compras.remove(i);
                i--;
            }
        }
        return compras;
    }

    List<CarritoProducto> obtenerCompra(Carritos carrito) {
        List<CarritoProducto> compras;
        EntityManager em = getEntityManager();
        System.out.println("Buscando carrito de compra por idCarrito : " + carrito);
        Query consulta = em.createNamedQuery("CarritoProducto.findAll");
        compras = consulta.getResultList();
        for (int i = 0; i < compras.size(); i++) {
            System.out.println(compras.get(i));
        }
        for (int i = 0; i < compras.size(); i++) {
            if (!compras.get(i).getIdCarrito().equals(carrito)) {
                System.out.println("Borre " + compras.get(i));
                compras.remove(i);
                i--;
            }
        }
        return compras;
    }
}
