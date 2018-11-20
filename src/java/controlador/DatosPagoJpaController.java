/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.Rentas;
import java.util.ArrayList;
import java.util.List;
import entidad.Compras;
import entidad.DatosPago;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.UserTransaction;

/**
 *
 * @author aaron
 */
public class DatosPagoJpaController implements Serializable {

    public DatosPagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosPago datosPago) throws RollbackFailureException, Exception {
        if (datosPago.getRentasList() == null) {
            datosPago.setRentasList(new ArrayList<Rentas>());
        }
        if (datosPago.getComprasList() == null) {
            datosPago.setComprasList(new ArrayList<Compras>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            utx = em.getTransaction();
            utx.begin();
            List<Rentas> attachedRentasList = new ArrayList<Rentas>();
            for (Rentas rentasListRentasToAttach : datosPago.getRentasList()) {
                rentasListRentasToAttach = em.getReference(rentasListRentasToAttach.getClass(), rentasListRentasToAttach.getIdRenta());
                attachedRentasList.add(rentasListRentasToAttach);
            }
            datosPago.setRentasList(attachedRentasList);
            List<Compras> attachedComprasList = new ArrayList<Compras>();
            for (Compras comprasListComprasToAttach : datosPago.getComprasList()) {
                comprasListComprasToAttach = em.getReference(comprasListComprasToAttach.getClass(), comprasListComprasToAttach.getIdCompra());
                attachedComprasList.add(comprasListComprasToAttach);
            }
            datosPago.setComprasList(attachedComprasList);
            em.persist(datosPago);
            for (Rentas rentasListRentas : datosPago.getRentasList()) {
                DatosPago oldIdDatosPagoOfRentasListRentas = rentasListRentas.getIdDatosPago();
                rentasListRentas.setIdDatosPago(datosPago);
                rentasListRentas = em.merge(rentasListRentas);
                if (oldIdDatosPagoOfRentasListRentas != null) {
                    oldIdDatosPagoOfRentasListRentas.getRentasList().remove(rentasListRentas);
                    oldIdDatosPagoOfRentasListRentas = em.merge(oldIdDatosPagoOfRentasListRentas);
                }
            }
            for (Compras comprasListCompras : datosPago.getComprasList()) {
                DatosPago oldIdDatosPagoOfComprasListCompras = comprasListCompras.getIdDatosPago();
                comprasListCompras.setIdDatosPago(datosPago);
                comprasListCompras = em.merge(comprasListCompras);
                if (oldIdDatosPagoOfComprasListCompras != null) {
                    oldIdDatosPagoOfComprasListCompras.getComprasList().remove(comprasListCompras);
                    oldIdDatosPagoOfComprasListCompras = em.merge(oldIdDatosPagoOfComprasListCompras);
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

    public void edit(DatosPago datosPago) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DatosPago persistentDatosPago = em.find(DatosPago.class, datosPago.getIdDatosPago());
            List<Rentas> rentasListOld = persistentDatosPago.getRentasList();
            List<Rentas> rentasListNew = datosPago.getRentasList();
            List<Compras> comprasListOld = persistentDatosPago.getComprasList();
            List<Compras> comprasListNew = datosPago.getComprasList();
            List<String> illegalOrphanMessages = null;
            for (Rentas rentasListOldRentas : rentasListOld) {
                if (!rentasListNew.contains(rentasListOldRentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rentas " + rentasListOldRentas + " since its idDatosPago field is not nullable.");
                }
            }
            for (Compras comprasListOldCompras : comprasListOld) {
                if (!comprasListNew.contains(comprasListOldCompras)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compras " + comprasListOldCompras + " since its idDatosPago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Rentas> attachedRentasListNew = new ArrayList<Rentas>();
            for (Rentas rentasListNewRentasToAttach : rentasListNew) {
                rentasListNewRentasToAttach = em.getReference(rentasListNewRentasToAttach.getClass(), rentasListNewRentasToAttach.getIdRenta());
                attachedRentasListNew.add(rentasListNewRentasToAttach);
            }
            rentasListNew = attachedRentasListNew;
            datosPago.setRentasList(rentasListNew);
            List<Compras> attachedComprasListNew = new ArrayList<Compras>();
            for (Compras comprasListNewComprasToAttach : comprasListNew) {
                comprasListNewComprasToAttach = em.getReference(comprasListNewComprasToAttach.getClass(), comprasListNewComprasToAttach.getIdCompra());
                attachedComprasListNew.add(comprasListNewComprasToAttach);
            }
            comprasListNew = attachedComprasListNew;
            datosPago.setComprasList(comprasListNew);
            datosPago = em.merge(datosPago);
            for (Rentas rentasListNewRentas : rentasListNew) {
                if (!rentasListOld.contains(rentasListNewRentas)) {
                    DatosPago oldIdDatosPagoOfRentasListNewRentas = rentasListNewRentas.getIdDatosPago();
                    rentasListNewRentas.setIdDatosPago(datosPago);
                    rentasListNewRentas = em.merge(rentasListNewRentas);
                    if (oldIdDatosPagoOfRentasListNewRentas != null && !oldIdDatosPagoOfRentasListNewRentas.equals(datosPago)) {
                        oldIdDatosPagoOfRentasListNewRentas.getRentasList().remove(rentasListNewRentas);
                        oldIdDatosPagoOfRentasListNewRentas = em.merge(oldIdDatosPagoOfRentasListNewRentas);
                    }
                }
            }
            for (Compras comprasListNewCompras : comprasListNew) {
                if (!comprasListOld.contains(comprasListNewCompras)) {
                    DatosPago oldIdDatosPagoOfComprasListNewCompras = comprasListNewCompras.getIdDatosPago();
                    comprasListNewCompras.setIdDatosPago(datosPago);
                    comprasListNewCompras = em.merge(comprasListNewCompras);
                    if (oldIdDatosPagoOfComprasListNewCompras != null && !oldIdDatosPagoOfComprasListNewCompras.equals(datosPago)) {
                        oldIdDatosPagoOfComprasListNewCompras.getComprasList().remove(comprasListNewCompras);
                        oldIdDatosPagoOfComprasListNewCompras = em.merge(oldIdDatosPagoOfComprasListNewCompras);
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
                Integer id = datosPago.getIdDatosPago();
                if (findDatosPago(id) == null) {
                    throw new NonexistentEntityException("The datosPago with id " + id + " no longer exists.");
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
            DatosPago datosPago;
            try {
                datosPago = em.getReference(DatosPago.class, id);
                datosPago.getIdDatosPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosPago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Rentas> rentasListOrphanCheck = datosPago.getRentasList();
            for (Rentas rentasListOrphanCheckRentas : rentasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatosPago (" + datosPago + ") cannot be destroyed since the Rentas " + rentasListOrphanCheckRentas + " in its rentasList field has a non-nullable idDatosPago field.");
            }
            List<Compras> comprasListOrphanCheck = datosPago.getComprasList();
            for (Compras comprasListOrphanCheckCompras : comprasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatosPago (" + datosPago + ") cannot be destroyed since the Compras " + comprasListOrphanCheckCompras + " in its comprasList field has a non-nullable idDatosPago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(datosPago);
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

    public List<DatosPago> findDatosPagoEntities() {
        return findDatosPagoEntities(true, -1, -1);
    }

    public List<DatosPago> findDatosPagoEntities(int maxResults, int firstResult) {
        return findDatosPagoEntities(false, maxResults, firstResult);
    }

    private List<DatosPago> findDatosPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosPago.class));
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

    public DatosPago findDatosPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosPago.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosPago> rt = cq.from(DatosPago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
