
package controlador;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.Usuarios;
import entidad.CarritoProducto;
import entidad.Carritos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CarritosJpaController implements Serializable {

    public CarritosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carritos carritos) throws RollbackFailureException, Exception {
        if (carritos.getCarritoProductoList() == null) {
            carritos.setCarritoProductoList(new ArrayList<CarritoProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            utx = em.getTransaction();
            utx.begin();
            Usuarios idUsuario = carritos.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                carritos.setIdUsuario(idUsuario);
            }
            List<CarritoProducto> attachedCarritoProductoList = new ArrayList<CarritoProducto>();
            for (CarritoProducto carritoProductoListCarritoProductoToAttach : carritos.getCarritoProductoList()) {
                carritoProductoListCarritoProductoToAttach = em.getReference(carritoProductoListCarritoProductoToAttach.getClass(), carritoProductoListCarritoProductoToAttach.getIdCarritoProducto());
                attachedCarritoProductoList.add(carritoProductoListCarritoProductoToAttach);
            }
            carritos.setCarritoProductoList(attachedCarritoProductoList);
            em.persist(carritos);
            if (idUsuario != null) {
                idUsuario.getCarritosList().add(carritos);
                idUsuario = em.merge(idUsuario);
            }
            for (CarritoProducto carritoProductoListCarritoProducto : carritos.getCarritoProductoList()) {
                Carritos oldIdCarritoOfCarritoProductoListCarritoProducto = carritoProductoListCarritoProducto.getIdCarrito();
                carritoProductoListCarritoProducto.setIdCarrito(carritos);
                carritoProductoListCarritoProducto = em.merge(carritoProductoListCarritoProducto);
                if (oldIdCarritoOfCarritoProductoListCarritoProducto != null) {
                    oldIdCarritoOfCarritoProductoListCarritoProducto.getCarritoProductoList().remove(carritoProductoListCarritoProducto);
                    oldIdCarritoOfCarritoProductoListCarritoProducto = em.merge(oldIdCarritoOfCarritoProductoListCarritoProducto);
                }
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

    public void edit(Carritos carritos) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            utx = em.getTransaction();
            utx.begin();
            Carritos persistentCarritos = em.find(Carritos.class, carritos.getIdCarrito());
            Usuarios idUsuarioOld = persistentCarritos.getIdUsuario();
            Usuarios idUsuarioNew = carritos.getIdUsuario();
            List<CarritoProducto> carritoProductoListOld = persistentCarritos.getCarritoProductoList();
            List<CarritoProducto> carritoProductoListNew = carritos.getCarritoProductoList();
            List<String> illegalOrphanMessages = null;
            for (CarritoProducto carritoProductoListOldCarritoProducto : carritoProductoListOld) {
                if (!carritoProductoListNew.contains(carritoProductoListOldCarritoProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CarritoProducto " + carritoProductoListOldCarritoProducto + " since its idCarrito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                carritos.setIdUsuario(idUsuarioNew);
            }
            List<CarritoProducto> attachedCarritoProductoListNew = new ArrayList<CarritoProducto>();
            for (CarritoProducto carritoProductoListNewCarritoProductoToAttach : carritoProductoListNew) {
                carritoProductoListNewCarritoProductoToAttach = em.getReference(carritoProductoListNewCarritoProductoToAttach.getClass(), carritoProductoListNewCarritoProductoToAttach.getIdCarritoProducto());
                attachedCarritoProductoListNew.add(carritoProductoListNewCarritoProductoToAttach);
            }
            carritoProductoListNew = attachedCarritoProductoListNew;
            carritos.setCarritoProductoList(carritoProductoListNew);
            carritos = em.merge(carritos);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getCarritosList().remove(carritos);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getCarritosList().add(carritos);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (CarritoProducto carritoProductoListNewCarritoProducto : carritoProductoListNew) {
                if (!carritoProductoListOld.contains(carritoProductoListNewCarritoProducto)) {
                    Carritos oldIdCarritoOfCarritoProductoListNewCarritoProducto = carritoProductoListNewCarritoProducto.getIdCarrito();
                    carritoProductoListNewCarritoProducto.setIdCarrito(carritos);
                    carritoProductoListNewCarritoProducto = em.merge(carritoProductoListNewCarritoProducto);
                    if (oldIdCarritoOfCarritoProductoListNewCarritoProducto != null && !oldIdCarritoOfCarritoProductoListNewCarritoProducto.equals(carritos)) {
                        oldIdCarritoOfCarritoProductoListNewCarritoProducto.getCarritoProductoList().remove(carritoProductoListNewCarritoProducto);
                        oldIdCarritoOfCarritoProductoListNewCarritoProducto = em.merge(oldIdCarritoOfCarritoProductoListNewCarritoProducto);
                    }
                }
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
                Integer id = carritos.getIdCarrito();
                if (findCarritos(id) == null) {
                    throw new NonexistentEntityException("The carritos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carritos carritos;
            try {
                carritos = em.getReference(Carritos.class, id);
                carritos.getIdCarrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carritos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CarritoProducto> carritoProductoListOrphanCheck = carritos.getCarritoProductoList();
            for (CarritoProducto carritoProductoListOrphanCheckCarritoProducto : carritoProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carritos (" + carritos + ") cannot be destroyed since the CarritoProducto " + carritoProductoListOrphanCheckCarritoProducto + " in its carritoProductoList field has a non-nullable idCarrito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios idUsuario = carritos.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getCarritosList().remove(carritos);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(carritos);
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

    public List<Carritos> findCarritosEntities() {
        return findCarritosEntities(true, -1, -1);
    }

    public List<Carritos> findCarritosEntities(int maxResults, int firstResult) {
        return findCarritosEntities(false, maxResults, firstResult);
    }

    private List<Carritos> findCarritosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carritos.class));
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

    public Carritos findCarritos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carritos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarritosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carritos> rt = cq.from(Carritos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
