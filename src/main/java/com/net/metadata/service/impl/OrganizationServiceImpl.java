package com.net.metadata.service.impl;

import com.google.common.collect.Lists;
import com.net.metadata.entity.Organization;
import com.net.metadata.mapper.OrganizationMapper;
import com.net.metadata.service.OrganizationService;
import com.net.metadata.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Tree> findTree() {
        List<Tree> trees = Lists.newArrayList();

        List<Organization> organizationFather = organizationMapper.findOrganizationAllByPidNull();

        if (organizationFather != null) {
            for (Organization organizationOne : organizationFather) {
                Tree treeOne = new Tree();
                treeOne.setId(organizationOne.getId());
                treeOne.setPid(organizationOne.getCode());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<Organization> organizationSon = organizationMapper.findOrganizationAllByPid(organizationOne.getId());

                if (organizationSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Organization organizationTwo : organizationSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId());
                        treeTwo.setPid(organizationTwo.getCode());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<Organization> findTreeGrid() {
        return organizationMapper.findOrganizationAll();
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationMapper.insert(organization);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationMapper.findOrganizationById(id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationMapper.updateOrganization(organization);
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationMapper.deleteOrganizationById(id);
    }

	@Override
	public Organization findOrganizationBycode(String code) {
		return organizationMapper.findOrganizationBycode(code);
	}

}
